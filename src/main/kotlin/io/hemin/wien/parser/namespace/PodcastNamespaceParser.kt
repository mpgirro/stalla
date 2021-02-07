package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodePodcastChaptersBuilder
import io.hemin.wien.builder.episode.EpisodePodcastSoundbiteBuilder
import io.hemin.wien.builder.episode.EpisodePodcastTranscriptBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.podcast.PodcastPodcastFundingBuilder
import io.hemin.wien.builder.podcast.PodcastPodcastLockedBuilder
import io.hemin.wien.dom.getAttributeByName
import io.hemin.wien.dom.textAsBooleanOrNull
import io.hemin.wien.model.Episode
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.isNullOrNegative
import io.hemin.wien.util.isNullOrNotPositive
import io.hemin.wien.util.trimmedOrNullIfBlank
import org.w3c.dom.Node
import java.time.Duration
import java.time.format.DateTimeParseException
import java.util.Locale

internal object PodcastNamespaceParser : NamespaceParser() {

    override val namespace: FeedNamespace = FeedNamespace.PODCAST

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        when (localName) {
            "locked" -> {
                val lockedBuilder = ifCanBeParsed { toLockedBuilder(builder.createPodcastPodcastLockedBuilder()) } ?: return
                builder.podcastBuilder.lockedBuilder(lockedBuilder)
            }
            "funding" -> {
                val fundingBuilder = ifCanBeParsed { toFundingBuilder(builder.createPodcastPodcastFundingBuilder()) } ?: return
                builder.podcastBuilder.addFundingBuilder(fundingBuilder)
            }
            else -> pass
        }
    }

    private fun Node.toLockedBuilder(lockedBuilder: PodcastPodcastLockedBuilder): PodcastPodcastLockedBuilder? {
        val owner = getAttributeByName("owner")?.value.trimmedOrNullIfBlank()
        val locked = textAsBooleanOrNull()

        if (owner == null || locked == null) return null
        return lockedBuilder.owner(owner)
            .locked(locked)
    }

    private fun Node.toFundingBuilder(fundingBuilder: PodcastPodcastFundingBuilder): PodcastPodcastFundingBuilder? {
        val url = getAttributeByName("url")?.value.trimmedOrNullIfBlank()
        val message = textContent.trimmedOrNullIfBlank()

        if (url == null || message == null) return null
        return fundingBuilder.url(url)
            .message(message)
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        when (localName) {
            "chapters" -> {
                val chaptersBuilder = ifCanBeParsed { toChaptersBuilder(builder.createEpisodePodcastChaptersBuilder()) } ?: return
                builder.podcastBuilder.chaptersBuilder(chaptersBuilder)
            }
            "soundbite" -> {
                val soundbiteBuilder = ifCanBeParsed { toSoundbiteBuilder(builder.createEpisodePodcastSoundbiteBuilder()) } ?: return
                builder.podcastBuilder.addSoundbiteBuilder(soundbiteBuilder)
            }
            "transcript" -> {
                val transcriptBuilder = ifCanBeParsed { toTranscriptBuilder(builder.createEpisodePodcastTranscriptBuilder()) } ?: return
                builder.podcastBuilder.addTranscriptBuilder(transcriptBuilder)
            }
            else -> pass
        }
    }

    private fun Node.toChaptersBuilder(chaptersBuilder: EpisodePodcastChaptersBuilder): EpisodePodcastChaptersBuilder? {
        val url = getAttributeByName("url")?.value.trimmedOrNullIfBlank()
        val type = getAttributeByName("type")?.value.trimmedOrNullIfBlank()

        if (url == null || type == null) return null
        return chaptersBuilder.url(url)
            .type(type)
    }

    private fun Node.toSoundbiteBuilder(soundbiteBuilder: EpisodePodcastSoundbiteBuilder): EpisodePodcastSoundbiteBuilder? {
        val startTime = getAttributeByName("startTime")?.value.trimmedOrNullIfBlank().parseAsDurationOrNull()
        val duration = getAttributeByName("duration")?.value.trimmedOrNullIfBlank().parseAsDurationOrNull()
        val title = textContent?.trimmedOrNullIfBlank()

        if (startTime.isNullOrNegative()) return null
        if (duration.isNullOrNotPositive()) return null

        return soundbiteBuilder.startTime(startTime as Duration)
            .duration(duration as Duration)
            .title(title)
    }

    private fun String?.parseAsDurationOrNull(): Duration? {
        if (this == null) return null

        return try {
            Duration.parse("PT${this}S")
        } catch (e: DateTimeParseException) {
            null
        }
    }

    private fun Node.toTranscriptBuilder(transcriptBuilder: EpisodePodcastTranscriptBuilder): EpisodePodcastTranscriptBuilder? {
        val url = getAttributeByName("url")?.value.trimmedOrNullIfBlank()
        val type = getAttributeByName("type")?.value.trimmedOrNullIfBlank()?.let { rawType ->
            Episode.Podcast.Transcript.Type.from(rawType)
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
