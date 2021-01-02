package io.hemin.wien.writer.namespace

import io.hemin.wien.model.Episode
import io.hemin.wien.model.ITunesBase
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import io.hemin.wien.util.appendElement
import io.hemin.wien.util.appendImageWithHrefElement
import io.hemin.wien.util.appendPersonElement
import io.hemin.wien.util.appendTrueFalseElement
import io.hemin.wien.util.appendYesElementIfTrue
import org.w3c.dom.Element

/**
 * Writer implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
internal class ITunesWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.ITUNES

    override fun writeChannelData(channel: Podcast, element: Element) {
        val iTunes = channel.iTunes ?: return

        element.appendCategoryElements(iTunes.categories)

        if (iTunes.complete != null) {
            element.appendYesElementIfTrue("complete", iTunes.complete, namespace)
        }

        if (iTunes.keywords != null) {
            element.appendElement("keywords", namespace) { textContent = iTunes.keywords }
        }

        if (iTunes.owner != null) {
            element.appendPersonElement("owner", iTunes.owner, namespace)
        }

        if (iTunes.type != null) {
            element.appendElement("type", namespace) { textContent = iTunes.type.type }
        }

        if (iTunes.type != null) {
            element.appendElement("new-feed-url", namespace) { textContent = iTunes.newFeedUrl }
        }

        element.appendCommonElements(channel.iTunes)
    }

    private fun Element.appendCategoryElements(categories: List<String>) {
        for (category in categories) {
            appendElement("category", namespace) {
                setAttribute("text", category)
            }
        }
    }

    override fun writeItemData(episode: Episode, element: Element) {
        val iTunes = episode.iTunes ?: return

        if (iTunes.duration != null) {
            element.appendElement("duration", namespace) { textContent = iTunes.duration }
        }

        if (iTunes.season != null) {
            element.appendElement("season", namespace) { textContent = iTunes.season.toString() }
        }

        if (iTunes.episode != null) {
            element.appendElement("episode", namespace) { textContent = iTunes.episode.toString() }
        }

        if (iTunes.episodeType != null) {
            element.appendElement("episodeType", namespace) { textContent = iTunes.episodeType.type }
        }

        element.appendCommonElements(episode.iTunes)
    }

    private fun Element.appendCommonElements(iTunes: ITunesBase) {
        val image = iTunes.image
        if (image != null) appendImageWithHrefElement(image, namespace)

        val explicit = iTunes.explicit
        if (explicit != null) appendTrueFalseElement("explicit", explicit, namespace)

        val title = iTunes.title
        if (title != null) {
            appendElement("title", namespace) { textContent = title }
        }

        val block = iTunes.block
        if (block != null) appendYesElementIfTrue("block", block, namespace)

        val author = iTunes.author
        if (author != null) {
            appendElement("author", namespace) { textContent = author }
        }

        val subtitle = iTunes.subtitle
        if (subtitle != null) {
            appendElement("subtitle", namespace) { textContent = subtitle }
        }

        val summary = iTunes.summary
        if (summary != null) {
            appendElement("summary", namespace) { textContent = summary }
        }
    }
}
