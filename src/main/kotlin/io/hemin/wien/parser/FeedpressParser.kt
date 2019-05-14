package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import org.w3c.dom.Node

class FeedpressParser : NamespaceParser() {

    override val namespaceURI: String? = "https://feed.press/xmlns"

    override fun parseChannel(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "newsletterId" -> builder.feedpress.newsletterId(toText(node))
            "locale"       -> builder.feedpress.locale(toText(node))
            "podcastId"    -> builder.feedpress.podcastId(toText(node))
            "cssFile"      -> builder.feedpress.cssFile(toText(node))
        }
    }

    /** This module does not set any data in the [EpisodeBuilder]. */
    override fun parseItem(builder: EpisodeBuilder, node: Node) { }

}
