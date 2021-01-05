package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Feedpress namespace.
 *
 * The namespace URI is: `https://feed.press/xmlns`
 */
internal class FeedpressWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.FEEDPRESS

    override fun writeChannelData(channel: Podcast, element: Element) {
        val feedpress = channel.feedpress ?: return

        if (feedpress.newsletterId != null) {
            element.appendElement("newsletterId", namespace) { textContent = feedpress.newsletterId }
        }

        if (feedpress.locale != null) {
            element.appendElement("locale", namespace) { textContent = feedpress.locale }
        }

        if (feedpress.podcastId != null) {
            element.appendElement("podcastId", namespace) { textContent = feedpress.podcastId }
        }

        if (feedpress.cssFile != null) {
            element.appendElement("cssFile", namespace) { textContent = feedpress.cssFile }
        }

        if (feedpress.link != null) {
            element.appendElement("link", namespace) { textContent = feedpress.link }
        }
    }

    override fun writeItemData(episode: Episode, element: Element) {
        // Nothing to do here
    }
}
