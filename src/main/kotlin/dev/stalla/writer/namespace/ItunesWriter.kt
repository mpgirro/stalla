package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.dom.appendHrefOnlyImageElement
import dev.stalla.dom.appendItunesOwnerElement
import dev.stalla.dom.appendItunesStyleCategoryElements
import dev.stalla.dom.appendTrueFalseElement
import dev.stalla.dom.appendYesElementIfTrue
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI2
import dev.stalla.util.isNeitherNullNorBlank
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
@InternalAPI2
internal object ItunesWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.ITUNES

    @Suppress("ComplexMethod")
    override fun Element.appendPodcastData(podcast: Podcast) {
        val itunes = podcast.itunes ?: return

        if (itunes.image != null) appendHrefOnlyImageElement(itunes.image, namespace)

        if (itunes.title.isNeitherNullNorBlank()) {
            appendElement("title", namespace) { textContent = itunes.title.trim() }
        }

        appendYesElementIfTrue("block", itunes.block, namespace)

        if (itunes.author.isNeitherNullNorBlank()) {
            appendElement("author", namespace) { textContent = itunes.author.trim() }
        }

        if (itunes.subtitle.isNeitherNullNorBlank()) {
            appendElement("subtitle", namespace) { textContent = itunes.subtitle.trim() }
        }

        if (itunes.summary.isNeitherNullNorBlank()) {
            appendElement("summary", namespace) { textContent = itunes.summary.trim() }
        }

        appendItunesStyleCategoryElements(itunes.categories, namespace)

        appendYesElementIfTrue("complete", itunes.complete, namespace)

        appendTrueFalseElement("explicit", itunes.explicit, namespace)

        if (itunes.keywords.isNeitherNullNorBlank()) {
            appendElement("keywords", namespace) { textContent = itunes.keywords.trim() }
        }

        if (itunes.owner != null) {
            appendItunesOwnerElement("owner", itunes.owner, namespace)
        }

        if (itunes.type != null) {
            appendElement("type", namespace) { textContent = itunes.type.type.trim() }
        }

        if (itunes.newFeedUrl.isNeitherNullNorBlank()) {
            appendElement("new-feed-url", namespace) { textContent = itunes.newFeedUrl.trim() }
        }
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val itunes = episode.itunes ?: return

        if (itunes.image != null) appendHrefOnlyImageElement(itunes.image, namespace)

        if (itunes.title.isNeitherNullNorBlank()) {
            appendElement("title", namespace) { textContent = itunes.title.trim() }
        }

        appendYesElementIfTrue("block", itunes.block, namespace)

        if (itunes.author.isNeitherNullNorBlank()) {
            appendElement("author", namespace) { textContent = itunes.author.trim() }
        }

        if (itunes.subtitle.isNeitherNullNorBlank()) {
            appendElement("subtitle", namespace) { textContent = itunes.subtitle.trim() }
        }

        if (itunes.summary.isNeitherNullNorBlank()) {
            appendElement("summary", namespace) { textContent = itunes.summary.trim() }
        }

        if (itunes.duration != null) {
            appendElement("duration", namespace) { textContent = itunes.duration.asFormattedString().trim() }
        }

        if (itunes.season != null) {
            appendElement("season", namespace) { textContent = itunes.season.toString() }
        }

        if (itunes.episode != null) {
            appendElement("episode", namespace) { textContent = itunes.episode.toString() }
        }

        if (itunes.episodeType != null) {
            appendElement("episodeType", namespace) { textContent = itunes.episodeType.type.trim() }
        }

        if (itunes.explicit != null) {
            appendTrueFalseElement("explicit", itunes.explicit, namespace)
        }
    }
}
