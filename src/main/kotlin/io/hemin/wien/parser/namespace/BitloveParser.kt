package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.getAttributeValueByName
import io.hemin.wien.dom.isDirectChildOf
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Node

/**
 * Parser implementation for the Bitlove namespace.
 *
 * The namespace URI is: `http://bitlove.org`
 */
internal class BitloveParser : NamespaceParser() {

    override val namespace = FeedNamespace.BITLOVE

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        // No-op
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        val guid = findGuid() ?: return
        builder.bitlove.guid(guid)
    }

    private fun Node.findGuid(): String? = getAttributeValueByName("guid", namespace)

    override fun canParse(node: Node) =
        node.namespaceURI == null && node.localName == "enclosure" && node.isDirectChildOf("item")
}
