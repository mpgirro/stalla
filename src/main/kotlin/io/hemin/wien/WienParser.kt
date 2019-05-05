package io.hemin.wien

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.parser.ContentParser
import io.hemin.wien.parser.ItunesParser
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.parser.RssParser
import io.hemin.wien.util.DomBuilderFactory
import io.hemin.wien.util.NodeListWrapper
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
            ItunesParser()
        )

        /** Set of all XML namespaces supported when parsing documents. */
        val supportedNamespaces: Set<String> =
            parsers
                .map { p -> p.namespaceURI }
                .mapNotNull { n -> n }
                .toSet()

        /**
         * Transforms a DOM node into a [Podcast] instance,
         * if the node is an RSS `<rss>`.
         */
        fun toPodcast(node: Node): Podcast? {
            return if (node.namespaceURI == null && node.localName == "channel") {
                val builder = PodcastBuilder()
                for (element in asList(node.childNodes)) {
                    for (parser in parsers) {
                        if (parser.namespaceURI.equals(element.namespaceURI)) {
                            parser.parse(builder, element)
                        }
                    }
                }
                builder.build()
            } else {
                null
            }
        }

        /**
         * Transforms a DOM node into a [Episode] instance,
         * if the node is an RSS `<item>`.
         */
        fun toEpisode(node: Node): Episode? {
            return if (node.namespaceURI == null && node.localName == "item") {
                val builder = EpisodeBuilder()
                for (element in asList(node.childNodes)) {
                    for (parser in parsers) {
                        if (parser.namespaceURI.equals(element.namespaceURI)) {
                            parser.parse(builder, element)
                        }
                    }
                }
                builder.build()
            } else {
                null
            }
        }
    }

    private val builder: DocumentBuilder = DomBuilderFactory.newBuilder()

    fun parse(uri: String) = parse(builder.parse(uri))

    fun parse(inputStream: InputStream) = parse(builder.parse(inputStream))

    /**
     * TODO
     *
     * @param inputStream InputStream containing the content to be parsed.
     * @param systemId Provide a base for resolving relative URIs.
     */
    fun parse(inputStream: InputStream, systemId: String) = parse(builder.parse(inputStream, systemId))

    fun parse(file: File) = parse(builder.parse(file))

    fun parse(inputSource: InputSource) = parse(builder.parse(inputSource))

    fun parse(doc: Document): Podcast? = findRssChannel(doc)?.let { toPodcast(it) }

    private fun findRssChannel(doc: Document): Node? = findRssChannel(doc.childNodes)

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

    private fun findRssChannel(nodes: NodeList): Node? {
        return asList(nodes)
            .map { n -> findRssChannel(n) }
            .filter { n -> n != null }
            .first()
    }
    
}
