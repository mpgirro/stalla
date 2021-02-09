package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.dom.getAttributeValueByName
import dev.stalla.dom.isDirectChildOf
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Bitlove namespace.
 *
 * The namespace URI is: `http://bitlove.org`
 */
@InternalApi
internal object BitloveParser : NamespaceParser() {

    override val namespace = FeedNamespace.BITLOVE

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        // No-op
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        val guid = findGuid() ?: return
        builder.bitloveBuilder.guid(guid)
    }

    private fun Node.findGuid(): String? = getAttributeValueByName("guid", namespace)

    override fun canParse(node: Node) =
        node.namespaceURI == null && node.localName == "enclosure" && node.isDirectChildOf("item")
}
