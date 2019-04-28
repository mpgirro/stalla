package io.hemin.wien.dom

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

    private fun walk(node: Node) {
        println("node :: uri=${node.namespaceURI} , localName=${node.localName} , nodeName=${node.nodeName} , textContent=${node.textContent.trim()}")
        walkChildren(node.childNodes)
    }

    private fun walkChildren(nodes: NodeList) {
        for (n in NodeListWrapper.asList(nodes)) {
            walk(n)
        }
    }

}
