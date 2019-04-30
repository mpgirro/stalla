package io.hemin.wien.parser

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import org.w3c.dom.Node
import java.time.ZonedDateTime

interface NamespaceParser {
    val namespace: String?
    fun parse(podcast: Podcast.Builder, node: Node)
    fun parse(episode: Episode.Builder, node: Node)

    /**
     * Extracts the text content of a DOM node
     */
    fun toText(node: Node): String? = node.textContent.trim()

    /**
     * Extracts the text content of a DOM node, and transforms it to a Date object
     */
    fun toDate(node: Node): ZonedDateTime? {
        try {
            return ZonedDateTime.parse(toText(node))
        }
        catch (e: Exception) {
            return null
        }
    }
}
