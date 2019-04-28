package io.hemin.wien.dom

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class DomParser {

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
                val p: Podcast = podcastFromChannel(n)
                println("Parsing produces Podcast: $p")
            }

            else -> {
                //println("Unexpected n :: uri=${n.namespaceURI} , localName=${n.localName} , nodeName=${n.nodeName} , textContent=${n.textContent.trim()}")
            }
        }

        //println("n :: uri=${n.namespaceURI} , localName=${n.localName} , nodeName=${n.nodeName} , textContent=${n.textContent.trim()}")
        //walkChildren(n.childNodes)
    }

    private fun walkChildren(nodes: NodeList) = walkChildren(NodeListWrapper.asList(nodes))

    private fun walkChildren(nodes: List<Node>) {
        for (n in nodes) {
            walk(n)
        }
    }

    private fun children(node: Node): List<Node> = NodeListWrapper.asList(node.childNodes)

    private fun podcastFromChannel(node: Node): Podcast {
        val p: Podcast.Builder = Podcast.Builder()

        // TODO
        for (n in children(node)) {
            when (Pair(n.namespaceURI, n.localName)) {
                Pair(null, "title") -> p.title(titleFromNode(n))
                Pair(null, "link") -> p.link(linkFromNode(n))
                Pair(null, "item") -> p.addEpisode(episodeFromItem(n))
            }
        }

        return p.build()
    }

    private fun episodeFromItem(node: Node): Episode {
        val e: Episode.Builder = Episode.Builder()

        // TODO
        for (n in children(node)) {
            when (Pair(n.namespaceURI, n.localName)) {
                Pair(null, "title") -> e.title(titleFromNode(n))
                Pair(null, "link") -> e.link(linkFromNode(n))
            }
        }

        return e.build()
    }

    private fun titleFromNode(n: Node): String? = n.textContent

    private fun linkFromNode(n: Node): String? = n.textContent

}
