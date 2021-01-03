package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import org.w3c.dom.Node

/**
 * Parser implementation for the Content namespace.
 *
 * The namespace URI is: `http://purl.org/rss/1.0/modules/content/`
 */
class ContentParser : NamespaceParser() {

    /**
     * The URI of the namespace processed by this parser.
     *
     * URI: `http://purl.org/rss/1.0/modules/content/`
     */
    override val namespaceURI: String? = "http://purl.org/rss/1.0/modules/content/"

    /** This module does not set any data in the [PodcastBuilder]. */
    override fun parse(builder: PodcastBuilder, node: Node) { }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "encoded" -> builder.content.encoded(toText(node))
            else -> pass
        }
    }
}
