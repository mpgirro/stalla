package io.hemin.wien.parser

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.isDirectChildOf
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Node

/** Base class for XML namespace parser implementations. */
internal abstract class NamespaceParser {

    /** The URI of the namespace processed by this parser. */
    abstract val namespace: FeedNamespace?

    /**
     * Parses a child [Node] that lives inside a `<channel>` node.
     * Extracts data from the XML namespace defined by [namespace]
     * and applies the values to properties of the [PodcastBuilder].
     * Parsing is only executed when the node's [Node.getNamespaceURI]
     * matches this parser's [namespace].
     *
     * @see canParse
     * @param builder The builder where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    fun tryParsingChannelChildNode(builder: PodcastBuilder, node: Node) {
        if (!canParse(node)) return
        require(node.isDirectChildOf("channel")) { "This function can only parse nodes that are direct children of <channel>" }
        parseChannelNode(builder, node)
    }

    /**
     * Extract all the data from a `<channel>` child node for the [namespace],
     * adding it to the provided builder as it goes.
     * **Note:** this method is only ever called when the node
     *
     * @param builder The builder where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    protected abstract fun parseChannelNode(builder: PodcastBuilder, node: Node)

    /**
     * Extracts data from the XML namespace defined by [namespace]
     * and applies the values to properties of the [EpisodeBuilder].
     *
     * Parsing is only executed when the parser supports the node, which
     * by default means that the node's namespaceURI property matches
     * the parser's [namespace]. Some parsers may change this behavior,
     * such as [io.hemin.wien.parser.namespace.BitloveParser].
     *
     * @see canParse
     * @param builder The builder where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    fun tryParsingItemChildNode(builder: EpisodeBuilder, node: Node) {
        if (!canParse(node)) return
        require(node.isDirectChildOf("item")) { "This function can only parse nodes that are direct children of <item>" }
        parseItemNode(builder, node)
    }

    /**
     * Extract all the data from a `<channel>` node for the [namespace],
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
     * Executes a block of code on the DOM node if the node has the same [namespace] of this parser.
     *
     * @param block The block of code to execute on the [this@ifMatchesNamespace] when the namespace matches the parser's.
     */
    internal fun <T> Node.ifCanBeParsed(block: Node.() -> T?) =
        if (canParse(this)) {
            block(this)
        } else {
            null
        }

    /**
     * Should return `true` when this parser can parse a given [Node]. By default true when the node namespace
     * matches the parser's [namespace], checking its [`uri`][FeedNamespace.uri].
     */
    protected open fun canParse(node: Node) = node.namespaceURI == this.namespace?.uri

    /** Explicitly do nothing. Used for exhaustive when blocks. */
    protected val pass: Unit = Unit
}
