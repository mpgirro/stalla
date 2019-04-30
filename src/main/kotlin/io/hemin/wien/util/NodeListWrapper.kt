package io.hemin.wien.util

import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.util.*

class NodeListWrapper (private val list: NodeList) : AbstractList<Node>(), RandomAccess {

    companion object Factory {
        fun asList(nodeList: NodeList): List<Node> {
            return if (nodeList.length == 0)
                Collections.emptyList()
            else
                NodeListWrapper(nodeList)
        }
    }

    override val size: Int
        get() = list.length

    override fun get(index: Int): Node = list.item(index)

}
