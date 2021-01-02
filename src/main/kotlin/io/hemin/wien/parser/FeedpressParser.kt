package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import org.w3c.dom.Node

/**
 * Parser implementation for the Feedpress namespace.
 *
 * The namespace URI is: `https://feed.press/xmlns`
 */
class FeedpressParser : NamespaceParser() {

    override val namespaceURI: String? = "https://feed.press/xmlns"

    override fun parse(builder: PodcastBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "newsletterId" -> builder.feedpress.newsletterId(toText(node))
            "locale" -> builder.feedpress.locale(toText(node))
            "podcastId" -> builder.feedpress.podcastId(toText(node))
            "cssFile" -> builder.feedpress.cssFile(toText(node))
            else -> pass
        }
    }

    /** This module does not set any data in the [EpisodeBuilder]. */
    override fun parse(builder: EpisodeBuilder, node: Node) { }
}
