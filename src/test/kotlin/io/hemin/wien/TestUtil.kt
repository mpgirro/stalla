package io.hemin.wien

import assertk.fail
import io.hemin.wien.util.DomBuilderFactory
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Document
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilder

private val domBuilder: DocumentBuilder = DomBuilderFactory.newBuilder()

/** Finds a DOM node matching [elementName] in a resource loaded from given [filePath]. */
internal fun nodeFromResource(elementName: String, filePath: String): Node {
    val doc = documentFromResource(filePath)

    var result: Node? = null
    for (node in doc.childNodes.asListOfNodes()) {
        if (elementName == node.localName) {
            result = node
        }
    }
    return result ?: fail("Unable to find the element '$elementName' in resource '$filePath'")
}

/** Creates a DOM document from a resource loaded from given [filePath]. */
internal fun documentFromResource(filePath: String): Document {
    val resourceUrl = DomBuilderFactory::class.java.getResource(filePath)
        ?: fail("The resource '$filePath' does not exist")

    return resourceUrl.openStream()
        .use { domBuilder.parse(it) }
}
