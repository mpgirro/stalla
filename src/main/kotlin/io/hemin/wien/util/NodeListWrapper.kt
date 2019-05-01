package io.hemin.wien.util

import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.util.*

/**
 * This class provides a [List] API for a [NodeList].
 *
 * @property list The [NodeList] to provide a [List] API for.
 */
class NodeListWrapper (private val list: NodeList) : AbstractList<Node>(), RandomAccess {

    companion object {

        /** Returns a [List] API for the argument. */
        fun asList(nodeList: NodeList): List<Node> {
            return if (nodeList.length == 0)
                Collections.emptyList()
            else
                NodeListWrapper(nodeList)
        }
    }

    /** Returns the number of elements in this list. */
    override val size: Int
        get() = list.length

    /** Returns the [Node] at the position [index] within the list. */
    override fun get(index: Int): Node = list.item(index)

}
