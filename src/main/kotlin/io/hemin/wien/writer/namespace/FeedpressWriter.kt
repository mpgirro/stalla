package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.isNeitherNullNorBlank
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

        if (feedpress.newsletterId.isNeitherNullNorBlank()) {
            appendElement("newsletterId", namespace) { textContent = feedpress.newsletterId?.trim() }
        }

        if (feedpress.locale.isNeitherNullNorBlank()) {
            appendElement("locale", namespace) { textContent = feedpress.locale?.trim() }
        }

        if (feedpress.podcastId.isNeitherNullNorBlank()) {
            appendElement("podcastId", namespace) { textContent = feedpress.podcastId?.trim() }
        }

        if (feedpress.cssFile.isNeitherNullNorBlank()) {
            appendElement("cssFile", namespace) { textContent = feedpress.cssFile?.trim() }
        }

        if (feedpress.link.isNeitherNullNorBlank()) {
            appendElement("link", namespace) { textContent = feedpress.link?.trim() }
        }
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        // Nothing to do here
    }
}
