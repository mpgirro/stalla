package io.hemin.wien

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import io.hemin.wien.parser.ContentParser
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.parser.RssParser
import io.hemin.wien.util.DomBuilderFactory
import io.hemin.wien.util.NodeListWrapper
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class WienParser {

    companion object {

        private val parsers: List<NamespaceParser> = listOf(
            RssParser(),
            ContentParser()
        )

        /** Transforms a DOM node into a [Podcast] instance. */
        fun toPodcast(node: Node): Podcast {
            val builder = PodcastBuilder()

            for (child in NodeListWrapper.asList(node.childNodes)) {
                for (parser in parsers) {
                    if (parser.namespaceURI.equals(child.namespaceURI)) {
                        parser.parse(builder, child)
                    }
                }
            }

            return builder.build()
        }

        /** Transforms a DOM node into a [Episode] instance. */
        fun toEpisode(node: Node): Episode {
            val builder = EpisodeBuilder()

            for (child in NodeListWrapper.asList(node.childNodes)) {
                for (parser in parsers) {
                    if (parser.namespaceURI.equals(child.namespaceURI)) {
                        parser.parse(builder, child)
                    }
                }
            }

            return builder.build()
        }
    }


    private val builder: DocumentBuilder = DomBuilderFactory.newBuilder()

    fun parse(uri: String) {
        val doc: Document = builder.parse(uri)
        walk(doc)
    }

    private fun walk(doc: Document) {
        walkChildren(doc.childNodes)
    }

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
