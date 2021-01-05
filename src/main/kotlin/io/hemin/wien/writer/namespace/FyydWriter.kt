package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Fyyd namespace.
 *
 * The namespace URI is: `https://fyyd.de/fyyd-ns/`
 */
internal class FyydWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.FYYD

    override fun writeChannelData(channel: Podcast, element: Element) {
        val fyyd = channel.fyyd ?: return

        element.appendElement("verify", namespace) { textContent = fyyd.verify }
    }

    override fun writeItemData(episode: Episode, element: Element) {
        // Nothing to do here
    }
}
