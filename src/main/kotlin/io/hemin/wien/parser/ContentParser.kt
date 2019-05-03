package io.hemin.wien.parser

import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import org.w3c.dom.Node

/**
 * Parser implementation for the Content namespace.
 *
 * The namespace URI is: `http://purl.org/rss/1.0/modules/content/`
 */
class ContentParser : NamespaceParser {

    /**
     * The URI of the namespace processed by this parser.
     *
     * URI: `http://purl.org/rss/1.0/modules/content/`
     */
    override val namespaceURI: String? = "http://purl.org/rss/1.0/modules/content/"

    /** This module does not set any data in the [PodcastBuilder]. */
    override fun parse(podcast: PodcastBuilder, node: Node) { }

    override fun parse(episode: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "encoded" -> episode.contentEncoded(toText(node))
        }
    }

}
