package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Fyyd namespace.
 *
 * The namespace URI is: `https://fyyd.de/fyyd-ns/`
 */
@InternalApi
internal object FyydWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.FYYD

    override fun Element.appendPodcastData(podcast: Podcast) {
        val verify = podcast.fyyd?.verify ?: return
        if (verify.isBlank()) return

        appendElement("verify", namespace) { textContent = verify.trim() }
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        // Nothing to do here
    }
}
