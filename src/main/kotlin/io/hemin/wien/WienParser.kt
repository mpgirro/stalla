package io.hemin.wien

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.parser.*
import io.hemin.wien.util.DomBuilderFactory
import io.hemin.wien.util.NodeListWrapper.Companion.asList
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.File
import java.io.InputStream
import javax.xml.parsers.DocumentBuilder

class WienParser {

    companion object {

        private val parsers: List<NamespaceParser> = listOf(
            RssParser(),
            ContentParser(),
            ItunesParser(),
            AtomParser()
        )

        /** Set of all XML namespaces supported when parsing documents. */
        val supportedNamespaces: Set<String> =
            parsers
                .map { p -> p.namespaceURI }
                .mapNotNull { n -> n }
                .toSet()

        /**
         * Transforms a DOM node into a [Podcast] instance, if the node is an RSS `<channel>` element.
         *
         * @param node The RSS element that must be an `<channel>`.
         * @return The [Episode] if the node is an `<channel>`, otherwise null.
         */
        fun toPodcast(node: Node): Podcast? = ensure("channel", node) {
            val builder = PodcastBuilder()
            for (element in asList(node.childNodes)) {
                for (parser in parsers) {
                    parser.parse(builder, element)
                }
            }
            builder.build()
        }

        /**
         * Transforms a DOM node into a [Episode] instance, if the node is an RSS `<item>` element.
         *
         * @param node The RSS element that must be an `<item>`.
         * @return The [Episode] if the node is an `<item>`, otherwise null.
         */
        fun toEpisode(node: Node): Episode? = ensure("item", node) {
            val builder = EpisodeBuilder()
            for (element in asList(node.childNodes)) {
                for (parser in parsers) {
                    parser.parse(builder, element)
                }
            }
            builder.build()
        }

        private fun <T> ensure(elementName: String?, node: Node, block: () -> T): T? {
            return if (node.namespaceURI == null && node.localName == elementName) {
                block()
            } else {
                null
            }
        }
    }

    private val builder: DocumentBuilder = DomBuilderFactory.newBuilder()

    /**
     * Parse the content of the given URI as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param uri The location of the content to be parsed.
     * @return A [Podcast] if the XML document behind the URI is an RSS document, otherwise null.
     */
    fun parse(uri: String): Podcast? = parse(builder.parse(uri))

    /**
     * Parse the content of the given input stream as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputStream InputStream containing the content to be parsed.
     * @return A [Podcast] if the XML document behind the input stream is an RSS document, otherwise null.
     */
    fun parse(inputStream: InputStream): Podcast? = parse(builder.parse(inputStream))

    /**
     * Parse the content of the given input stream as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputStream InputStream containing the content to be parsed.
     * @param systemId Provide a base for resolving relative URIs.
     * @return A [Podcast] if the XML document behind the input stream is an RSS document, otherwise null.
     */
    fun parse(inputStream: InputStream, systemId: String): Podcast? = parse(builder.parse(inputStream, systemId))

    /**
     * Parse the content of the given file as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param file File containing the content to be parsed.
     * @return A [Podcast] if the XML document behind the file is an RSS document, otherwise null.
     */
    fun parse(file: File): Podcast? = parse(builder.parse(file))

    /**
     * Parse the content of the given input source as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputSource InputSource containing the content to be parsed.
     * @return A [Podcast] if the XML document behind the input source is an RSS document, otherwise null.
     */
    fun parse(inputSource: InputSource): Podcast? = parse(builder.parse(inputSource))

    /**
     * Parse the content of the given XML document and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param document Document containing the content to be parsed.
     * @return A [Podcast] if the XML document is an RSS document, otherwise null.
     */
    fun parse(document: Document): Podcast? = findRssChannel(document)?.let { toPodcast(it) }

    private fun findRssChannel(doc: Document): Node? = findRssChannel(doc.childNodes)

    private fun findRssChannel(nodes: NodeList): Node? {
        return asList(nodes)
            .map { n -> findRssChannel(n) }
            .first { n -> n != null }
    }

    private fun findRssChannel(node: Node): Node? {
        return if (node.namespaceURI == null) {
            when (node.localName) {
                "rss" -> {
                    if (node.childNodes.length <= 0) {
                        null
                    } else {
                        findRssChannel(node.childNodes)
                    }
                }
                "channel" ->
                    node
                else ->
                    null
            }
        } else {
            null
        }
    }

}
