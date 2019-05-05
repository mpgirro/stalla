package io.hemin.wien.util

import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.util.*

/**
 * This class provides a [List] API for a [NodeList].
 *
 * @property nodes The [NodeList] to provide a [List] API for.
 */
class NodeListWrapper (private val nodes: NodeList) : AbstractList<Node>(), RandomAccess {

    companion object {

        /**
         * Returns a [List] API for the argument.
         *
         * @param nodeList The instance holding the DOM node elements.
         * @retunr The [List] API of the argument.
         */
        fun asList(nodeList: NodeList): List<Node> {
            return if (nodeList.length == 0)
                Collections.emptyList()
            else
                NodeListWrapper(nodeList)
        }
    }

    /** Returns the number of elements in this nodes. */
    override val size: Int = nodes.length

    /**
     * Returns the [Node] at the position [index] within the nodes.
     *
     * @return The element at the index.
     */
    override fun get(index: Int): Node = nodes.item(index)

}
