package io.hemin.wien

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Model
import io.hemin.wien.model.ModelBuilder
import io.hemin.wien.model.Podcast
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.NodeListWrapper
import io.hemin.wien.parser.RssParser
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class WienParser {

    companion object {

        private val parsers: List<NamespaceParser> = listOf(RssParser())

        fun toPodcast(node: Node): Podcast {
            val builder: Podcast.Builder = Podcast.Builder()

            for (child in NodeListWrapper.asList(node.childNodes)) {
                for (parser in parsers) {
                    if (parser.namespace.equals(child.namespaceURI)) {
                        parser.parse(builder, child)
                    }
                }
            }

            return builder.build()
        }

        fun toEpisode(node: Node): Episode {
            val builder: Episode.Builder = Episode.Builder()

            for (child in NodeListWrapper.asList(node.childNodes)) {
                for (parser in parsers) {
                    if (parser.namespace.equals(child.namespaceURI)) {
                        parser.parse(builder, child)
                    }
                }
            }

            return builder.build()
        }
    }

    val factory: DocumentBuilderFactory
    val builder: DocumentBuilder

    init {
        factory = DocumentBuilderFactory.newInstance()
        factory.isNamespaceAware = true
        builder = factory.newDocumentBuilder()
    }

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
