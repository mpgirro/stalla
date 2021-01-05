package io.hemin.wien.writer.namespace

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import io.hemin.wien.util.appendElement
import org.w3c.dom.Element

/**
 * Writer implementation for the Content namespace.
 *
 * The namespace URI is: `http://purl.org/rss/1.0/modules/content/`
 */
internal class ContentWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.CONTENT

    override fun writeChannelData(channel: Podcast, element: Element) {
        // Nothing to do here
    }

    override fun writeItemData(episode: Episode, element: Element) {
        if (episode.content?.encoded == null) return

        element.appendElement("encoded", namespace) { textContent = episode.content.encoded }
    }
}
