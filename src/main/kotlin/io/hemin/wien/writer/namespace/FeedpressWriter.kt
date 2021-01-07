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

    override fun Element.appendPodcastData(podcast: Podcast) {
        val feedpress = podcast.feedpress ?: return

        if (feedpress.newsletterId != null) {
            appendElement("newsletterId", namespace) { textContent = feedpress.newsletterId }
        }

        if (feedpress.locale != null) {
            appendElement("locale", namespace) { textContent = feedpress.locale }
        }

        if (feedpress.podcastId != null) {
            appendElement("podcastId", namespace) { textContent = feedpress.podcastId }
        }

        if (feedpress.cssFile != null) {
            appendElement("cssFile", namespace) { textContent = feedpress.cssFile }
        }

        if (feedpress.link != null) {
            appendElement("link", namespace) { textContent = feedpress.link }
        }
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        // Nothing to do here
    }
}
