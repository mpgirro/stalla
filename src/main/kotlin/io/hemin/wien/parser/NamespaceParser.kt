package io.hemin.wien.parser

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.util.trimmedOrNullIfBlank
import org.w3c.dom.Node
import java.time.temporal.TemporalAccessor
import java.util.Locale

/** Base class for XML namespace parser implementations. */
internal abstract class NamespaceParser {

    /** The URI of the namespace processed by this parser. */
    abstract val namespaceURI: String?

    /**
     * Parses a child [Node] that lives inside a `<channel>` node.
     * Extracts data from the XML namespace defined by [namespaceURI]
     * and applies the values to properties of the [PodcastBuilder].
     * Parsing is only executed when the node's [Node.getNamespaceURI]
     * matches this parser's [namespaceURI].
     *
     * @see canParseNode
     * @param builder The builder where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    fun tryParsingChannelChildNode(builder: PodcastBuilder, node: Node) {
        if (!node.canParseNode()) return
        require(node.isDirectChildOf("channel")) { "This function can only parse nodes that are direct children of <channel>" }
        parseChannelNode(builder, node)
    }

    /**
     * Extract all the data from a `<channel>` child node for the [namespaceURI],
     * adding it to the provided builder as it goes.
     * **Note:** this method is only ever called when the node
     *
     * @param builder The builder where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    protected abstract fun parseChannelNode(builder: PodcastBuilder, node: Node)

    /**
     * Extracts data from the XML namespace defined by [namespaceURI]
     * and applies the values to properties of the [EpisodeBuilder].
     *
     * Parsing is only executed when the parser supports the node, which
     * by default means that the node's namespaceURI property matches
     * the parser's [namespaceURI]. Some parsers may change this behavior,
     * such as [io.hemin.wien.parser.namespace.BitloveParser].
     *
     * @see canParseNode
     * @param builder The builder where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    fun tryParsingItemChildNode(builder: EpisodeBuilder, node: Node) {
        if (!node.canParseNode()) return
        require(node.isDirectChildOf("item")) { "This function can only parse nodes that are direct children of <item>" }
        parseItemNode(builder, node)
    }

    /**
     * Extract all the data from a `<channel>` node for the [namespaceURI],
     * adding it to the provided builder as it goes.
     * **Note:** this method is only ever called when the node has the correct
     * namespace, and it is indeed a direct child of `<item>`.
     *
     * @param builder The builder where all parsed data is added to.
     *
     * @param node The DOM node from which all data is extracted from.
     *
     */
    protected abstract fun parseItemNode(builder: EpisodeBuilder, node: Node)

    /**
     * Extracts the [Node.getTextContent] of a DOM node. Trims whitespace at the beginning and the end.
     * Returns `null` if the text is blank.
     *
     * @return The content of the DOM node in string representation, or null.
     */
    protected fun Node.textOrNull(): String? = this.ifMatchesNamespace() {
        it.textContent.trimmedOrNullIfBlank()
    }

    /**
     * Extracts the [Node.getTextContent] of a DOM node, and tries to parse it as a boolean.
     * If the textContent cannot be parsed, returns `null`.
     *
     * @see parseAsBooleanOrNull
     * @return The logical interpretation of the DOM node's text content as boolean, or `null`.
     */
    protected fun Node.textAsBooleanOrNull(): Boolean? = this.ifMatchesNamespace() {
        it.textOrNull().parseAsBooleanOrNull()
    }

    /**
     * Interprets a string content as a boolean. If the string value cannot be parsed, returns `null`.
     * Supports values of `yes`/`no`, or `true`/`false`, case insensitive.
     *
     * @return The logical interpretation of the string parameter, or `null`.
     */
    protected fun String?.parseAsBooleanOrNull() = when (this?.toLowerCase(Locale.ROOT)) {
        "true", "yes" -> true
        "false", "no" -> false
        else -> null
    }

    /**
     * Extracts the text content of a DOM node, and transforms it to an Int instance.
     *
     * @return The DOM nodes content as an Int, or null if conversion failed.
     */
    protected fun Node.toInt(): Int? = this.ifMatchesNamespace() {
        it.textOrNull()?.toIntOrNull()
    }

    /**
     * Extracts the text content of a DOM node, and parses it as a [TemporalAccessor] instance
     * if possible.
     *
     * @return The DOM nodes content as an Int, or null if parsing failed.
     */
    protected fun Node.toDate(): TemporalAccessor? = this.ifMatchesNamespace() {
        DateParser.parse(it.textOrNull())
    }

    /**
     * Extract the textContent of a DOM node attribute identified by name.
     *
     * @param attributeName The name of the node's attribute.
     * @return The textContent of the node's attribute.
     */
    protected fun Node.attributeValueByName(attributeName: String): String? =
        attributes?.getNamedItem(attributeName)?.textContent?.trim()

    /**
     * Executes a block of code on a DOM node if the node has the same [namespaceURI] of this parser.
     *
     * @param this@ifMatchesNamespace The DOM node to execute the [block] of code on.
     * @param block The block of code to execute on the [this@ifMatchesNamespace] when the namespace matches the parser's.
     */
    protected fun <T> Node.ifMatchesNamespace(block: (Node) -> T?) =
        if (canParseNode()) {
            block(this)
        } else {
            null
        }

    protected open fun Node.canParseNode() = namespaceURI == this@NamespaceParser.namespaceURI

    protected fun Node.isDirectChildOf(tagName: String) = parentNode.nodeName == tagName && parentNode.namespaceURI == null

    /** Explicitly do nothing. Used for exhaustive when blocks. */
    protected val pass: Unit = Unit
}
