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
            "newsletterId" -> builder.feedpress.newsletterId(node.textOrNull())
            "locale" -> builder.feedpress.locale(node.textOrNull())
            "podcastId" -> builder.feedpress.podcastId(node.textOrNull())
            "cssFile" -> builder.feedpress.cssFile(node.textOrNull())
            "link" -> builder.feedpress.link(node.textOrNull())
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        // No-op
    }
}
