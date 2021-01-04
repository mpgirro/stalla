package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.parser.NamespaceParser
import org.w3c.dom.Node

/**
 * Parser implementation for the Fyyd namespace.
 *
 * The namespace URI is: `https://fyyd.de/fyyd-ns/`
 */
internal class FyydParser : NamespaceParser() {

    override val namespaceURI: String = "https://fyyd.de/fyyd-ns/"

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "verify" -> {
                val verify = node.textOrNull() ?: return
                builder.fyyd.verify(verify)
            }
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        // No-op
    }
}
