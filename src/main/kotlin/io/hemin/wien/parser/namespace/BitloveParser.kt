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

    override fun parse(builder: PodcastBuilder, node: Node) {}

    override fun parse(builder: EpisodeBuilder, node: Node) {
        if (node.namespaceURI == null && node.localName == "enclosure") {
            val guid = toGuid(node) ?: return
            builder.bitlove.guid(guid)
        }
    }

    /**
     * Extracts the Bitlove GUID attribute from the DOM node.
     *
     * @return The DOM nodes GUID attribute value from the Bitlove namespace, if present.
     */
    private fun toGuid(node: Node): String? =
        node.attributes?.getNamedItemNS(namespaceURI, "guid")?.textContent?.trim()
}
