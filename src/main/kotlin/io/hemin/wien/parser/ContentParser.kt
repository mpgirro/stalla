package io.hemin.wien.parser

import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import org.w3c.dom.Node

/** Parser implementation for the Content namespace. */
class ContentParser : NamespaceParser {

    override val namespaceURI: String? = "http://purl.org/rss/1.0/modules/content/"

    /** This module does not contribute any data to the [PodcastBuilder]. */
    override fun parse(podcast: PodcastBuilder, node: Node) { }

    override fun parse(episode: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "encoded" -> episode.contentEncoded(toText(node))
        }
    }

}
