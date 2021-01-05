package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.getAttributeValueByName
import io.hemin.wien.util.isDirectChildOf
import org.w3c.dom.Node

/**
 * Parser implementation for the Bitlove namespace.
 *
 * The namespace URI is: `http://bitlove.org`
 */
internal class BitloveParser : NamespaceParser() {

    override val namespace = FeedNamespace.BITLOVE

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        // No-op
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        val guid = node.findGuid() ?: return
        builder.bitlove.guid(guid)
    }

    private fun Node.findGuid(): String? = getAttributeValueByName("guid", namespace)

    override fun canParse(node: Node) =
        node.namespaceURI == null && node.localName == "enclosure" && node.isDirectChildOf("item")
}
