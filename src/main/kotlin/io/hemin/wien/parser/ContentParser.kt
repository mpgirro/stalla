package io.hemin.wien.parser

import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import org.w3c.dom.Node

class ContentParser : NamespaceParser {

    override val namespace: String? = "http://purl.org/rss/1.0/modules/content/"

    override fun parse(podcast: PodcastBuilder, node: Node) { }

    override fun parse(episode: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "encoded" -> episode.contentEncoded(toText(node))
        }
    }

}
