package dev.stalla.parser.namespace

import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.textOrNull
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Fyyd namespace.
 *
 * The namespace URI is: `https://fyyd.de/fyyd-ns/`
 */
@InternalApi
internal object FyydParser : NamespaceParser() {

    override val namespace = FeedNamespace.FYYD

    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        when (localName) {
            "verify" -> {
                val verify = ifCanBeParsed { textOrNull() } ?: return
                builder.fyydBuilder.verify(verify)
            }
            else -> pass
        }
    }

    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        // No-op
    }
}
