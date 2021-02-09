package dev.stalla.parser

import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.dom.isDirectChildOf
import dev.stalla.util.FeedNamespace
import dev.stalla.util.FeedNamespace.Companion.matches
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/** Base class for XML namespace parser implementations. */
@InternalApi
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
     * @param node The DOM node from which all data is extracted from.
     * @param builder The builder where all parsed data is added to.
     */
    fun tryParsingChannelChildNode(node: Node, builder: PodcastBuilder) {
        if (!canParse(node)) return
        require(node.isDirectChildOf("channel")) { "This function can only parse nodes that are direct children of <channel>" }
        node.parseChannelData(builder)
    }

    /**
     * Extract all the data from a `<channel>` child node for the [namespace],
     * adding it to the provided builder as it goes.
     * **Note:** this method is only ever called when the [Node] receiver has
     * the correct namespace, and it is indeed a direct child of `<channel>`.
     *
     * @param builder The builder where all parsed data is added to.
     */
    protected abstract fun Node.parseChannelData(builder: PodcastBuilder)

    /**
     * Extracts data from the XML namespace defined by [namespace]
     * and applies the values to properties of the [EpisodeBuilder].
     *
     * Parsing is only executed when the parser supports the node, which
     * by default means that the node's namespaceURI property matches
     * the parser's [namespace]. Some parsers may change this behavior,
     * such as [dev.stalla.parser.namespace.BitloveParser].
     *
     * @see canParse
     * @param node The DOM node from which all data is extracted from.
     * @param builder The builder where all parsed data is added to.
     */
    fun tryParsingItemChildNode(node: Node, builder: EpisodeBuilder) {
        if (!canParse(node)) return
        require(node.isDirectChildOf("item")) { "This function can only parse nodes that are direct children of <item>" }
        node.parseItemData(builder)
    }

    /**
     * Extract all the data from a `<channel>` node for the [namespace],
     * adding it to the provided builder as it goes.
     * **Note:** this method is only ever called when the [Node] receiver has
     * the correct namespace, and it is indeed a direct child of `<item>`.
     *
     * @param builder The builder where all parsed data is added to.
     */
    protected abstract fun Node.parseItemData(builder: EpisodeBuilder)

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
     * matches the parser's [namespace], checking its `uri`s with [FeedNamespace.Companion.matches].
     */
    protected open fun canParse(node: Node) = this.namespace.matches(node.namespaceURI)

    /** Explicitly do nothing. Used for exhaustive when blocks. */
    protected val pass: Unit = Unit
}
