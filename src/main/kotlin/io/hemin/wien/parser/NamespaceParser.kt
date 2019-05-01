package io.hemin.wien.parser

import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import org.w3c.dom.Node
import java.time.ZonedDateTime

interface NamespaceParser {

    /** The URI of the namespace processed by this parser. */
    val namespaceURI: String?

    /** Extracts data from the XML namespace defined by [namespaceURI]
     * and applies the values to properties of the [PodcastBuilder]. */
    fun parse(podcast: PodcastBuilder, node: Node)

    /** Extracts data from the XML namespace defined by [namespaceURI]
     * and applies the values to properties of the [EpisodeBuilder]. */
    fun parse(episode: EpisodeBuilder, node: Node)

    /** Extracts the text content of a DOM node */
    fun toText(node: Node): String? = node.textContent.trim()

    /** Extracts the text content of a DOM node, and transforms it to a Date object. */
    fun toDate(node: Node): ZonedDateTime? {
        return try {
            ZonedDateTime.parse(toText(node))
        }
        catch (e: Exception) {
            null
        }
    }
}
