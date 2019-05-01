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

    /** Returns the number of elements in this list. */
    override val size: Int = list.length

    /**
     * Returns the [Node] at the position [index] within the list.
     *
     * @return The element at the index.
     */
    override fun get(index: Int): Node = list.item(index)

}
