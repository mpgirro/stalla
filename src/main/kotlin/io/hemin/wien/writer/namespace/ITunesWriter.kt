package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.appendHrefOnlyImageElement
import io.hemin.wien.dom.appendITunesCategoryElements
import io.hemin.wien.dom.appendPersonElement
import io.hemin.wien.dom.appendTrueFalseElement
import io.hemin.wien.dom.appendYesElementIfTrue
import io.hemin.wien.model.Episode
import io.hemin.wien.model.ITunesBase
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.isNeitherNullNorBlank
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
internal class ITunesWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.ITUNES

    override fun Element.appendPodcastData(podcast: Podcast) {
        val iTunes = podcast.iTunes ?: return

        appendITunesCategoryElements(iTunes.categories, namespace)

        if (iTunes.complete != null) {
            appendYesElementIfTrue("complete", iTunes.complete, namespace)
        }

        if (iTunes.keywords.isNeitherNullNorBlank()) {
            appendElement("keywords", namespace) { textContent = iTunes.keywords }
        }

        if (iTunes.owner != null) {
            appendPersonElement("owner", iTunes.owner, namespace)
        }

        if (iTunes.type != null) {
            appendElement("type", namespace) { textContent = iTunes.type.type }
        }

        if (iTunes.newFeedUrl.isNeitherNullNorBlank()) {
            appendElement("new-feed-url", namespace) { textContent = iTunes.newFeedUrl }
        }

        appendCommonElements(podcast.iTunes)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val iTunes = episode.iTunes ?: return

        if (iTunes.duration.isNeitherNullNorBlank()) {
            appendElement("duration", namespace) { textContent = iTunes.duration }
        }

        if (iTunes.season != null) {
            appendElement("season", namespace) { textContent = iTunes.season.toString() }
        }

        if (iTunes.episode != null) {
            appendElement("episode", namespace) { textContent = iTunes.episode.toString() }
        }

        if (iTunes.episodeType != null) {
            appendElement("episodeType", namespace) { textContent = iTunes.episodeType.type }
        }

        appendCommonElements(episode.iTunes)
    }

    private fun Element.appendCommonElements(iTunes: ITunesBase) {
        val image = iTunes.image
        if (image != null) appendHrefOnlyImageElement(image, namespace)

        val explicit = iTunes.explicit
        if (explicit != null) appendTrueFalseElement("explicit", explicit, namespace)

        val title = iTunes.title
        if (title.isNeitherNullNorBlank()) {
            appendElement("title", namespace) { textContent = title }
        }

        val block = iTunes.block
        if (block != null) appendYesElementIfTrue("block", block, namespace)

        val author = iTunes.author
        if (author.isNeitherNullNorBlank()) {
            appendElement("author", namespace) { textContent = author }
        }

        val subtitle = iTunes.subtitle
        if (subtitle.isNeitherNullNorBlank()) {
            appendElement("subtitle", namespace) { textContent = subtitle }
        }

        val summary = iTunes.summary
        if (summary.isNeitherNullNorBlank()) {
            appendElement("summary", namespace) { textContent = summary }
        }
    }
}
