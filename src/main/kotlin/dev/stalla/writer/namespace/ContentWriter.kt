package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Content namespace.
 *
 * The namespace URI is: `http://purl.org/rss/1.0/modules/content/`
 */
@InternalAPI
internal object ContentWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.CONTENT

    override fun Element.appendPodcastData(podcast: Podcast) {
        // Nothing to do here
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val encoded = episode.content?.encoded
        if (encoded.isNullOrBlank()) return

        appendElement("encoded", namespace) { textContent = encoded.trim() }
    }
}
