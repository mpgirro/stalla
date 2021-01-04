package io.hemin.wien.util

import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

internal fun Node.findElementByTagName(nodeName: String, filter: (Node) -> Boolean = { true }) =
    childNodes.asListOfNodes()
        .firstOrNull { it.nodeName == nodeName && filter(it) }
        ?.asElement()

internal fun Node.asElement() = this as Element

internal fun NodeList.isNotEmpty() = length > 0
