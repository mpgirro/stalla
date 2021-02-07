package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Content namespace.
 *
 * The namespace URI is: `http://purl.org/rss/1.0/modules/content/`
 */
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
