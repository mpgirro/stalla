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

        /** Transforms a DOM node into a [Podcast] instance. */
        fun toPodcast(node: Node): Podcast {
            val builder = PodcastBuilder()
            for (element in asList(node.childNodes)) {
                for (parser in parsers) {
                    if (parser.namespaceURI.equals(element.namespaceURI)) {
                        parser.parse(builder, element)
                    }
                }
            }
            return builder.build()
        }

        /** Transforms a DOM node into a [Episode] instance. */
        fun toEpisode(node: Node): Episode {
            val builder = EpisodeBuilder()
            for (element in asList(node.childNodes)) {
                for (parser in parsers) {
                    if (parser.namespaceURI.equals(element.namespaceURI)) {
                        parser.parse(builder, element)
                    }
                }
            }
            return builder.build()
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

    fun parse(doc: Document) = walkChildren(doc.childNodes)

    private fun walk(n: Node) {

        when (Pair(n.namespaceURI, n.localName)) {
            Pair(null, "rss") -> {
                // we expect a <channel> next
                when (n.childNodes.length) {
                    0 ->
                        println("<rss> has no child nodes")
                    1 ->
                        walk(n.firstChild)
                    else ->
                        //println("<rss> has multiple (as in unexpected) child nodes")
                        walkChildren(n.childNodes)
                }
            }

            Pair(null, "channel") -> {
                val p: Podcast = toPodcast(n)
                println("Parsing produces Podcast: $p")
            }

            else -> {
                //println("Unexpected n :: uri=${n.namespaceURI} , localName=${n.localName} , nodeName=${n.nodeName} , textContent=${n.textContent.trim()}")
            }
        }

    }

    private fun walkChildren(nodes: NodeList) = walkChildren(NodeListWrapper.asList(nodes))

    private fun walkChildren(nodes: List<Node>) {
        for (n in nodes) {
            walk(n)
        }
    }

}
