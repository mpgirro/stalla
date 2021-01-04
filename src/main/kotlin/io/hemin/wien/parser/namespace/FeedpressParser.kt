package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.parser.NamespaceParser
import org.w3c.dom.Node

/**
 * Parser implementation for the Feedpress namespace.
 *
 * The namespace URI is: `https://feed.press/xmlns`
 */
internal class FeedpressParser : NamespaceParser() {

    override val namespaceURI: String = "https://feed.press/xmlns"

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "newsletterId" -> builder.feedpress.newsletterId(textOrNull(node))
            "locale" -> builder.feedpress.locale(textOrNull(node))
            "podcastId" -> builder.feedpress.podcastId(textOrNull(node))
            "cssFile" -> builder.feedpress.cssFile(textOrNull(node))
            "link" -> builder.feedpress.link(textOrNull(node))
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        // No-op
    }
}
