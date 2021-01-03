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

    override fun parse(builder: PodcastBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "verify" -> {
                val verify = toText(node) ?: return@valid
                builder.fyyd.verify(verify)
            }
            else -> pass
        }
    }

    /** This module does not set any data in the [EpisodeBuilder]. */
    override fun parse(builder: EpisodeBuilder, node: Node) {}
}
