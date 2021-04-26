package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.model.podcastindex.PodcastindexPerson
import dev.stalla.util.BooleanStringStyle
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI
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
@InternalAPI
internal object PodcastindexWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.PODCAST

    override fun Element.appendPodcastData(podcast: Podcast) {
        val podcastindex = podcast.podcastindex ?: return

        if (podcastindex.locked != null && podcastindex.locked.owner.isNotBlank()) {
            appendElement("locked", namespace) {
                setAttribute("owner", podcastindex.locked.owner.trim())
                textContent = podcastindex.locked.locked.asBooleanString(BooleanStringStyle.YES_NO)
            }
        }

        for (funding in podcastindex.funding) {
            if (funding.url.isBlank() || funding.message.isBlank()) continue

            appendElement("funding", namespace) {
                setAttribute("url", funding.url.trim())
                textContent = funding.message.trim()
            }
        }

        appendPodcastindexPersonData(podcastindex.persons)

        appendPodcastindexLocationData(podcastindex.location)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val podcastindex = episode.podcastindex ?: return

        if (podcastindex.chapters != null && podcastindex.chapters.canBeWritten()) {
            appendElement("chapters", namespace) {
                setAttribute("url", podcastindex.chapters.url.trim())
                setAttribute("type", podcastindex.chapters.type.toString())
            }
        }

        for (transcript in podcastindex.transcripts) {
            if (transcript.url.isBlank()) continue

            appendElement("transcript", namespace) {
                setAttribute("url", transcript.url.trim())
                setAttribute("type", transcript.type.toString().trim())
                if (transcript.language != null) setAttribute("language", transcript.language.toLanguageTag())
                if (transcript.rel != null) setAttribute("rel", transcript.rel.trim())
            }
        }

        for (soundbite in podcastindex.soundbites) {
            if (soundbite.startTime.isNegative || !soundbite.duration.isStrictlyPositive) continue

            appendElement("soundbite", namespace) {
                setAttribute("startTime", soundbite.startTime.asFormattedString().trim())
                setAttribute("duration", soundbite.duration.asFormattedString().trim())
                if (soundbite.title != null) textContent = soundbite.title.trim()
            }
        }

        appendPodcastindexPersonData(podcastindex.persons)

        appendPodcastindexLocationData(podcastindex.location)

        if (podcastindex.season != null) {
            appendElement("season", namespace) {
                if (podcastindex.season.name != null) setAttribute("name", podcastindex.season.name.trim())
                textContent = podcastindex.season.number.toString()
            }
        }

        if (podcastindex.episode != null) {
            appendElement("episode", namespace) {
                if (podcastindex.episode.display != null) setAttribute("display", podcastindex.episode.display.trim())
                textContent = podcastindex.episode.number.toString()
            }
        }
    }

    private fun Element.appendPodcastindexPersonData(persons: List<PodcastindexPerson>) {
        for (person in persons) {
            if (person.name.isBlank()) continue

            appendElement("person", namespace) {
                if (person.role != null) setAttribute("role", person.role.trim())
                if (person.group != null) setAttribute("group", person.group.trim())
                if (person.img != null) setAttribute("img", person.img.trim())
                if (person.href != null) setAttribute("href", person.href.trim())
                textContent = person.name.trim()
            }
        }
    }

    private fun Element.appendPodcastindexLocationData(location: PodcastindexLocation?) {
        if (location != null && location.name.isNotBlank()) {
            appendElement("location", namespace) {
                if (location.geo != null) setAttribute("geo", location.geo.toString())
                if (location.osm != null) setAttribute("osm", location.osm.toString())
                textContent = location.name
            }
        }
    }

    private fun Chapters.canBeWritten() = url.isNotBlank()
}
