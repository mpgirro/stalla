package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import org.w3c.dom.Node
import java.util.*

/** Base class for XML namespace parser implementations. */
abstract class NamespaceParser {

    /** The URI of the namespace processed by this parser. */
    abstract val namespaceURI: String?

    /**
     * Extracts data from the XML namespace defined by [namespaceURI]
     * and applies the values to properties of the [PodcastBuilder].
     * Parsing is only executed when the node's namespaceURI property
     * matches this parsers [namespaceURI].
     *
     * @param builder The builder  where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    fun parse(builder: PodcastBuilder, node: Node) = ensure(node) {
        parseChannel(builder, node)
    }

    /** The actual namespace parsing implementation for an RSS `<channel>`. */
    protected abstract fun parseChannel(builder: PodcastBuilder, node: Node)

    /**
     * Extracts data from the XML namespace defined by [namespaceURI]
     * and applies the values to properties of the [EpisodeBuilder].
     * Parsing is only executed when the node's namespaceURI property
     * matches this parsers [namespaceURI].
     *
     * @param builder The builder  where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    fun parse(builder: EpisodeBuilder, node: Node) = ensure(node) {
        parseItem(builder, node)
    }

    /** The actual namespace parsing implementation for an RSS `<item>`. */
    protected abstract fun parseItem(builder: EpisodeBuilder, node: Node)

    /**
     * Extracts the text content of a DOM node. Trims whitespace at the beginning and the end.
     *
     * @return The content of the DOM node in string representation.
     */
    fun toText(node: Node): String? = node.textContent.trim()

    /**
     * Extracts the text content of a DOM node, and transforms it to a Date instance.
     *
     * @return The DOM nodes content as a Date, or null if date parsing failed.
     */
    fun toDate(node: Node): Date? {
        return try {
            DateParser.parse(toText(node))
        }
        catch (e: Exception) {
            null
        }
    }

    /**
     * Interprets a string's content as a boolean. If the textContent cannot be recognizes, null is returned.
     *
     * @param sBool The string representation of a boolean.
     * @return The logical interpretation of the string parameter.
     */
    fun toBoolean(sBool: String?) = when(sBool) {
        "true"  -> true
        "yes"   -> true
        "false" -> false
        "no"    -> false
        else    -> null
    }

    /**
     * Interprets a DOM node's stringcontent as a boolean.
     * If the textContent cannot be recognizes, null is returned.
     *
     * @return The logical interpretation of the DOM node's text content.
     */
    fun toBoolean(node: Node): Boolean? = toBoolean(toText(node))

    /**
     * Extracts the text content of a DOM node, and transforms it to an Int instance.
     *
     * @return The DOM nodes content as an Int, or null if conversion failed.
     */
    fun toInt(node: Node): Int? = toText(node)?.toIntOrNull()

    /**
     * Extract the textContent of a DOM node attribute identified by name.
     *
     * @param node The DOM node with the attribute.
     * @param attributeName The name of the node's attribute.
     * @return The textContent of the node's attribute.
     */
    fun attributeValueByName(node: Node, attributeName: String): String? =
        node.attributes?.getNamedItem(attributeName)?.textContent?.trim()

    private fun ensure(node: Node, block: () -> Unit) {
        if (node.namespaceURI == this.namespaceURI) {
            block()
        }
    }

}
