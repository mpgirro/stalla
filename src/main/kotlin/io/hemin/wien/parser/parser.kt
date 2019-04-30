package io.hemin.wien.parser

import io.hemin.wien.model.Episode
import org.w3c.dom.Node
import java.time.ZonedDateTime

/**
 * Extracts the text content of a DOM node
 */
fun toText(node: Node): String? = node.textContent

/**
 * Extracts the text content of a DOM node, and transforms it to a Date object
 */
fun toDate(node: Node): ZonedDateTime? {
    try {
        return ZonedDateTime.parse(node.textContent)
    }
    catch (e: Exception) {
        return null
    }
}

fun toEnclosure(node: Node): Episode.Enclosure {

    fun value(name: String): String? = node.attributes.getNamedItem(name).textContent

    val url: String? = value("url")
    val length: Long? = value("length")?.toLongOrNull()
    val type: String? = value("type")

    return Episode.Enclosure.Builder(url, length , type).build()
}
