package io.hemin.wien.writer.namespace

import io.hemin.wien.model.Episode
import io.hemin.wien.model.GooglePlayBase
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import io.hemin.wien.util.appendElement
import io.hemin.wien.util.appendImageElement
import io.hemin.wien.util.appendYesElementIfTrue
import org.w3c.dom.Element

/**
 * Writer implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
internal class GooglePlayWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.GOOGLE_PLAY

    override fun writeChannelData(channel: Podcast, element: Element) {
        val play = channel.googlePlay ?: return

        if (play.author != null) {
            element.appendElement("author", namespace) { textContent = play.author }
        }

        if (play.owner != null) {
            element.appendElement("owner", namespace) { textContent = play.owner }
        }

        element.appendCategoryElements(play.categories)

        element.appendCommonElements(play)
    }

    private fun Element.appendCategoryElements(categories: List<String>) {
        for (category in categories) {
            appendElement("category", namespace) {
                setAttribute("text", category)
            }
        }
    }

    override fun writeItemData(episode: Episode, element: Element) {
        val play = episode.googlePlay ?: return

        element.appendCommonElements(play)
    }

    private fun Element.appendCommonElements(play: GooglePlayBase) {
        val description = play.description
        if (description != null) {
            appendElement("description", namespace) { textContent = description }
        }

        val explicit = play.explicit
        if (explicit != null) {
            appendYesElementIfTrue("explicit", explicit, namespace)
        }

        val block = play.block
        if (block != null) {
            appendYesElementIfTrue("block", block, namespace)
        }

        val image = play.image
        if (image != null) {
            appendImageElement(image, namespace)
        }
    }
}
