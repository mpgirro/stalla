package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.parser.NamespaceParser
import org.w3c.dom.Node

/**
 * Parser implementation for the Bitlove namespace.
 *
 * The namespace URI is: `http://bitlove.org`
 */
internal class BitloveParser : NamespaceParser() {

    override val namespaceURI: String = "http://bitlove.org"

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        // No-op
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        val guid = toGuid(node) ?: return
        builder.bitlove.guid(guid)
    }

    override fun Node.canParseNode() = namespaceURI == null && localName == "enclosure" && isDirectChildOf("item")

    private fun toGuid(node: Node): String? =
        node.attributes?.getNamedItemNS(namespaceURI, "guid")?.textContent?.trim()
}
