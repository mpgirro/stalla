package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.textOrNull
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Node

/**
 * Parser implementation for the Feedpress namespace.
 *
 * The namespace URI is: `https://feed.press/xmlns`
 */
internal class FeedpressParser : NamespaceParser() {

    override val namespace = FeedNamespace.FEEDPRESS

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "newsletterId" -> builder.feedpress.newsletterId(node.ifCanBeParsed { textOrNull() })
            "locale" -> builder.feedpress.locale(node.ifCanBeParsed { textOrNull() })
            "podcastId" -> builder.feedpress.podcastId(node.ifCanBeParsed { textOrNull() })
            "cssFile" -> builder.feedpress.cssFile(node.ifCanBeParsed { textOrNull() })
            "link" -> builder.feedpress.link(node.ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        // No-op
    }
}
