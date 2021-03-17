package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.util.BooleanStringStyle
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI2
import dev.stalla.util.asBooleanString
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Podcast namespace.
 *
 * The namespace URI is: `https://podcastindex.org/namespace/1.0`
 * but `https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md`
 * should also be accepted as equivalent. TODO allow both NS
 */
@InternalAPI2
internal object PodcastindexWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.PODCAST

    override fun Element.appendPodcastData(podcast: Podcast) {
        val podcastNs = podcast.podcastindex ?: return

        if (podcastNs.locked != null && podcastNs.locked.owner.isNotBlank()) {
            appendElement("locked", namespace) {
                setAttribute("owner", podcastNs.locked.owner.trim())
                textContent = podcastNs.locked.locked.asBooleanString(BooleanStringStyle.YES_NO)
            }
        }

        for (funding in podcastNs.funding) {
            if (funding.url.isBlank() || funding.message.isBlank()) continue

            appendElement("funding", namespace) {
                setAttribute("url", funding.url.trim())
                textContent = funding.message.trim()
            }
        }
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val podcastNs = episode.podcastindex ?: return

        if (podcastNs.chapters != null && podcastNs.chapters.canBeWritten()) {
            appendElement("chapters", namespace) {
                setAttribute("url", podcastNs.chapters.url.trim())
                setAttribute("type", podcastNs.chapters.type.toString())
            }
        }

        for (transcript in podcastNs.transcripts) {
            if (transcript.url.isBlank()) continue

            appendElement("transcript", namespace) {
                setAttribute("url", transcript.url.trim())
                setAttribute("type", transcript.type.toString().trim())
                if (transcript.language != null) setAttribute("language", transcript.language.toLanguageTag())
                if (transcript.rel != null) setAttribute("rel", transcript.rel.trim())
            }
        }

        for (soundbite in podcastNs.soundbites) {
            if (soundbite.startTime.isNegative || !soundbite.duration.isStrictlyPositive) continue

            appendElement("soundbite", namespace) {
                setAttribute("startTime", soundbite.startTime.asFormattedString().trim())
                setAttribute("duration", soundbite.duration.asFormattedString().trim())
                if (soundbite.title != null) textContent = soundbite.title.trim()
            }
        }
    }

    private fun Chapters.canBeWritten() = url.isNotBlank()
}
