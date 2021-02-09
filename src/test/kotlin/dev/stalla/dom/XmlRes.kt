package dev.stalla.dom

import assertk.fail
import dev.stalla.documentFromResource
import org.w3c.dom.Node

internal data class XmlRes(private val filePath: String) {

    private val document = documentFromResource(filePath)

    /** Finds a DOM node at the root level matching [elementName]. */
    fun rootNodeByName(elementName: String): Node =
        document.findElementByName(elementName)
            ?: fail("Unable to find the element '$elementName' in resource '$filePath'")

    /** Finds a DOM node matching [xPath]. */
    fun nodeByXPath(xPath: String): Node =
        document.findNodeByXPath(xPath)
            ?: fail("Unable to find the element with XPath '$xPath' in resource '$filePath'")
}
