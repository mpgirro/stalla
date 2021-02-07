package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.InternalApi
import io.hemin.wien.writer.NamespaceWriter
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
