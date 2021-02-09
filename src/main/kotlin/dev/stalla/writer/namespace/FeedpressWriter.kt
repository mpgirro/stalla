package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import dev.stalla.util.isNeitherNullNorBlank
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Feedpress namespace.
 *
 * The namespace URI is: `https://feed.press/xmlns`
 */
@InternalApi
internal object FeedpressWriter : NamespaceWriter() {

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
