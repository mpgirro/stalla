package dev.stalla.dom

import dev.stalla.util.InternalAPI
import org.w3c.dom.Node
import org.w3c.dom.NodeList

/**
 * Provides a [List] API for a [NodeList] by wrapping it.
 *
 * @property nodes The [NodeList] to provide a [List] API for.
 */
@InternalAPI
internal class NodeListWrapper(private val nodes: NodeList) : AbstractList<Node>(), RandomAccess {

    /** Returns the number of elements in this nodes. */
    override val size: Int = nodes.length

    /**
     * Returns the [Node] at the position [index] within the nodes.
     *
     * @return The element at the index.
     */
    override fun get(index: Int): Node = nodes.item(index)
}
