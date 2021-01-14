package io.hemin.wien.dom

import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.namespace.NamespaceContext
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

private val xPath = XPathFactory.newDefaultInstance()
    .newXPath()
    .apply { namespaceContext = FeedNamespaceContext }

internal fun Node.findNodeByXPath(nodePath: String) =
    xPath.evaluate(nodePath, this, XPathConstants.NODE) as? Node

internal fun Node.findNodesByXPath(nodePath: String) =
    xPath.evaluate(nodePath, this, XPathConstants.NODESET) as? NodeList

private object FeedNamespaceContext : NamespaceContext {

    override fun getNamespaceURI(prefix: String?): String? {
        if (prefix == null) return null
        return FeedNamespace.values().find { it.prefix == prefix }?.uri
    }

    override fun getPrefix(namespaceURI: String?): String? {
        if (namespaceURI == null) return null
        return FeedNamespace.values().find { it.uri == namespaceURI }?.prefix
    }

    override fun getPrefixes(namespaceURI: String?): Iterator<String> {
        val prefix = getPrefix(namespaceURI) ?: return emptyList<String>().iterator()
        return listOf(prefix).iterator()
    }
}
