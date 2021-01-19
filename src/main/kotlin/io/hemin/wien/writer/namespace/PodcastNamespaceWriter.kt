package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.BooleanStringStyle
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.asBooleanString
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element
import java.time.Duration

/**
 * Writer implementation for the Podcast namespace.
 *
 * The namespace URI is: `https://podcastindex.org/namespace/1.0`
 * but `https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md`
 * should also be accepted as equivalent. TODO allow both NS
 */
internal class PodcastNamespaceWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.PODCAST

    override fun Element.appendPodcastData(podcast: Podcast) {
        val podcastNs = podcast.podcast ?: return

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
        val podcastNs = episode.podcast ?: return

        if (podcastNs.chapters != null && podcastNs.chapters.canBeWritten()) {
            appendElement("chapters", namespace) {
                setAttribute("url", podcastNs.chapters.url.trim())
                setAttribute("type", podcastNs.chapters.type.trim())
            }
        }

        for (transcript in podcastNs.transcripts) {
            if (transcript.url.isBlank()) continue

            appendElement("transcript", namespace) {
                setAttribute("url", transcript.url.trim())
                setAttribute("type", transcript.type.rawType.trim())
                if (transcript.language != null) setAttribute("language", transcript.language.toLanguageTag())
                if (transcript.rel != null) setAttribute("rel", transcript.rel.trim())
            }
        }

        for (soundbite in podcastNs.soundbites) {
            if (soundbite.startTime.isNegative || !soundbite.duration.isPositive()) continue

            appendElement("soundbite", namespace) {
                setAttribute("startTime", soundbite.startTime.toSecondsString())
                setAttribute("duration", soundbite.duration.toSecondsString())
                if (soundbite.title != null) textContent = soundbite.title.trim()
            }
        }
    }

    private fun Episode.Podcast.Chapters.canBeWritten() = url.isNotBlank() && type.isNotBlank()

    private fun Duration.isPositive(): Boolean = !(isNegative || isZero)

    private fun Duration.toSecondsString(): String =
        if (toMillisPart() == 0) {
            seconds.toString()
        } else {
            "${seconds}.${toMillisPart().toString().padStart(3, '0')}"
        }
}
