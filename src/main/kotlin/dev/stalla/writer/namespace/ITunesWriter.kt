package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.dom.appendHrefOnlyImageElement
import dev.stalla.dom.appendITunesStyleCategoryElements
import dev.stalla.dom.appendPersonElement
import dev.stalla.dom.appendTrueFalseElement
import dev.stalla.dom.appendYesElementIfTrue
import dev.stalla.model.Episode
import dev.stalla.model.ITunesBase
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import dev.stalla.util.isNeitherNullNorBlank
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
@InternalApi
internal object ITunesWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.ITUNES

    override fun Element.appendPodcastData(podcast: Podcast) {
        val iTunes = podcast.iTunes ?: return

        appendITunesStyleCategoryElements(iTunes.categories, namespace)

        appendYesElementIfTrue("complete", iTunes.complete, namespace)

        appendTrueFalseElement("explicit", iTunes.explicit, namespace)

        if (iTunes.keywords.isNeitherNullNorBlank()) {
            appendElement("keywords", namespace) { textContent = iTunes.keywords?.trim() }
        }

        if (iTunes.owner != null) {
            appendPersonElement("owner", iTunes.owner, namespace)
        }

        if (iTunes.type != null) {
            appendElement("type", namespace) { textContent = iTunes.type.type.trim() }
        }

        if (iTunes.newFeedUrl.isNeitherNullNorBlank()) {
            appendElement("new-feed-url", namespace) { textContent = iTunes.newFeedUrl?.trim() }
        }

        appendCommonElements(podcast.iTunes)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val iTunes = episode.iTunes ?: return

        if (iTunes.duration.isNeitherNullNorBlank()) {
            appendElement("duration", namespace) { textContent = iTunes.duration?.trim() }
        }

        if (iTunes.season != null) {
            appendElement("season", namespace) { textContent = iTunes.season.toString() }
        }

        if (iTunes.episode != null) {
            appendElement("episode", namespace) { textContent = iTunes.episode.toString() }
        }

        if (iTunes.episodeType != null) {
            appendElement("episodeType", namespace) { textContent = iTunes.episodeType.type.trim() }
        }

        if (iTunes.explicit != null) {
            appendTrueFalseElement("explicit", iTunes.explicit, namespace)
        }

        appendCommonElements(episode.iTunes)
    }

    private fun Element.appendCommonElements(iTunes: ITunesBase) {
        val image = iTunes.image
        if (image != null) appendHrefOnlyImageElement(image, namespace)

        val title = iTunes.title
        if (title.isNeitherNullNorBlank()) {
            appendElement("title", namespace) { textContent = title?.trim() }
        }

        appendYesElementIfTrue("block", iTunes.block, namespace)

        val author = iTunes.author
        if (author.isNeitherNullNorBlank()) {
            appendElement("author", namespace) { textContent = author?.trim() }
        }

        val subtitle = iTunes.subtitle
        if (subtitle.isNeitherNullNorBlank()) {
            appendElement("subtitle", namespace) { textContent = subtitle?.trim() }
        }

        val summary = iTunes.summary
        if (summary.isNeitherNullNorBlank()) {
            appendElement("summary", namespace) { textContent = summary?.trim() }
        }
    }
}