package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.getAttributeByName
import dev.stalla.dom.textAsBooleanOrNull
import dev.stalla.model.StyledDuration
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import dev.stalla.util.trimmedOrNullIfBlank
import org.w3c.dom.Node
import java.util.Locale

/**
 * Parser implementation for the PodcastIndex namespace.
 *
 * The namespace URI is: `https://podcastindex.org/namespace/1.0`
 */
@InternalApi
internal object PodcastindexParser : NamespaceParser() {

    override val namespace: FeedNamespace = FeedNamespace.PODCAST

    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        when (localName) {
            "locked" -> ifCanBeParsed { toLockedBuilder(builder.createPodcastPodcastLockedBuilder()) }
                ?.let(builder.podcastPodcastindexBuilder::lockedBuilder)
            "funding" -> ifCanBeParsed { toFundingBuilder(builder.createPodcastPodcastFundingBuilder()) }
                ?.let(builder.podcastPodcastindexBuilder::addFundingBuilder)
            else -> pass
        }
    }

    private fun Node.toLockedBuilder(
        lockedBuilder: PodcastPodcastindexLockedBuilder
    ): PodcastPodcastindexLockedBuilder? {
        val owner = getAttributeByName("owner")?.value.trimmedOrNullIfBlank()
        val locked = textAsBooleanOrNull()

        if (owner == null || locked == null) return null
        return lockedBuilder.owner(owner)
            .locked(locked)
    }

    private fun Node.toFundingBuilder(
        fundingBuilder: PodcastPodcastindexFundingBuilder
    ): PodcastPodcastindexFundingBuilder? {
        val url = getAttributeByName("url")?.value.trimmedOrNullIfBlank()
        val message = textContent.trimmedOrNullIfBlank()

        if (url == null || message == null) return null
        return fundingBuilder.url(url)
            .message(message)
    }

    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        when (localName) {
            "chapters" -> ifCanBeParsed { toChaptersBuilder(builder.createEpisodePodcastChaptersBuilder()) }
                ?.let(builder.podcastindexBuilder::chaptersBuilder)
            "soundbite" -> ifCanBeParsed { toSoundbiteBuilder(builder.createEpisodePodcastSoundbiteBuilder()) }
                ?.let(builder.podcastindexBuilder::addSoundbiteBuilder)
            "transcript" -> ifCanBeParsed { toTranscriptBuilder(builder.createEpisodePodcastTranscriptBuilder()) }
                ?.let(builder.podcastindexBuilder::addTranscriptBuilder)
            else -> pass
        }
    }

    private fun Node.toChaptersBuilder(
        chaptersBuilder: EpisodePodcastindexChaptersBuilder
    ): EpisodePodcastindexChaptersBuilder? {
        val url = getAttributeByName("url")?.value.trimmedOrNullIfBlank()
        val type = getAttributeByName("type")?.value.trimmedOrNullIfBlank()

        if (url == null || type == null) return null
        return chaptersBuilder.url(url)
            .type(type)
    }

    private fun Node.toSoundbiteBuilder(
        soundbiteBuilder: EpisodePodcastindexSoundbiteBuilder
    ): EpisodePodcastindexSoundbiteBuilder? {
        val startTime = StyledDuration.of(getAttributeByName("startTime")?.value.trimmedOrNullIfBlank())
            ?.ensureFractionalFormat()
        val duration = StyledDuration.of(getAttributeByName("duration")?.value.trimmedOrNullIfBlank())
            ?.ensureFractionalFormat()
        val title = textContent?.trimmedOrNullIfBlank()

        if (startTime !is StyledDuration.SecondsAndFraction || startTime.isNegative) return null
        if (duration !is StyledDuration.SecondsAndFraction || !duration.isStrictlyPositive) return null

        return soundbiteBuilder.startTime(startTime)
            .duration(duration)
            .title(title)
    }

    private fun StyledDuration.ensureFractionalFormat(): StyledDuration.SecondsAndFraction =
        if (this !is StyledDuration.SecondsAndFraction) {
            StyledDuration.SecondsAndFraction(rawDuration)
        } else this

    private fun Node.toTranscriptBuilder(
        transcriptBuilder: EpisodePodcastindexTranscriptBuilder
    ): EpisodePodcastindexTranscriptBuilder? {
        val url = getAttributeByName("url")?.value.trimmedOrNullIfBlank()
        val type = getAttributeByName("type")?.value.trimmedOrNullIfBlank()?.let { rawType ->
            TranscriptType.of(rawType)
        }
        val language = getAttributeByName("language")?.value.trimmedOrNullIfBlank()?.let { rawLocale ->
            Locale.forLanguageTag(rawLocale)
        }
        val rel = getAttributeByName("rel")?.value.trimmedOrNullIfBlank()

        if (url == null || type == null) return null
        return transcriptBuilder.url(url)
            .type(type)
            .language(language)
            .rel(rel)
    }
}
