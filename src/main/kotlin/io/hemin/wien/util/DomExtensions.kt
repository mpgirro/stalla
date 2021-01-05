package io.hemin.wien.util

import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.model.Person
import io.hemin.wien.model.RssImage
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList

/** Finds the first element matching the given (local)[name], [namespace] and [filter], if any. */
internal fun Node.findElementByName(
    name: String,
    namespace: FeedNamespace? = null,
    filter: (Node) -> Boolean = { true }
) = childNodes.asListOfNodes()
    .firstOrNull { it.getTagName() == name && it.namespaceURI == namespace?.uri && filter(it) }
    ?.asElement()

private fun Node.getTagName(): String? = when (this) {
    is Element -> localName ?: tagName ?: nodeName
    else -> localName ?: nodeName
}

/**
 * Checks whether the node is a direct child of a tag with the given name.
 */
internal fun Node.isDirectChildOf(tagName: String) =
    parentNode.nodeName == tagName && parentNode.namespaceURI == null

/**
 * Extract the [`textContent`][Node.getTextContent] of a DOM node attribute identified by name.
 *
 * @param attributeName The name of the node's attribute.
 * @param namespace The namespace to use, if any.
 * @return The textContent of the node's attribute.
 */
internal fun Node.getAttributeValueByName(attributeName: String, namespace: FeedNamespace? = null): String? =
    attributes?.getNamedItemNS(namespace?.uri, attributeName)?.textContent?.trim()

/** Returns true if the [NodeList] contains at least one node. */
internal fun NodeList.isNotEmpty() = length > 0

/** Converts this [NodeList] to a [List] of [Node]s. */
internal fun NodeList.asListOfNodes(): List<Node> {
    if (length == 0) return emptyList()
    return NodeListWrapper(this)
}

/** Converts this [NamedNodeMap] to a [List] of [Attr]s. */
internal fun NamedNodeMap.asListOfAttrs(): List<Attr> {
    if (length == 0) return emptyList()
    return (0 until length).map { index -> item(index) }
        .filterIsInstance(Attr::class.java)
}

/** Casts the [Node] to an [Element]. */
internal fun Node.asElement() = this as Element

/**
 * Appends a new `<namespace:image href="..."/>` with the given [namespace] to this [Element].
 * Currently the only supported [FeedNamespace] values are:
 *  * [FeedNamespace.ITUNES]
 *  * [FeedNamespace.GOOGLE_PLAY]
 *
 * @param image The image to represent with the new element.
 * @param namespace The namespace to use for the new element.
 */
internal fun Node.appendImageElement(image: HrefOnlyImage, namespace: FeedNamespace): Element {
    require(namespace == FeedNamespace.ITUNES || namespace == FeedNamespace.GOOGLE_PLAY) {
        "Only 'itunes:image' and 'googleplay:image' tags are supported, but the desired prefix was '${namespace.prefix}:'"
    }
    return appendElement("image", namespace) {
        setAttribute("href", image.href)
    }
}

/**
 * Appends an [RSS `<image>` tag][https://www.w3schools.com/XML/rss_tag_image.asp] to this [Element].
 *
 * @param image The image to represent with the new element.
 */
internal fun Node.appendImageElement(image: RssImage): Element = appendElement("image") {
    appendElement("title") { textContent = image.title }
    appendElement("link") { textContent = image.link }
    appendElement("url") { textContent = image.url }
    if (image.description != null) appendElement("description") { textContent = image.description }
    if (image.height != null) appendElement("height") { textContent = image.height.toString() }
    if (image.width != null) appendElement("width") { textContent = image.width.toString() }
}

/**
 * Appends a new [Element] with the given [tag name][elementTagName] to this [Element].
 * You can configure the new element by specifiying a lambda for [init].
 *
 * @param elementTagName The tag name for the new element.
 * @param namespace The namespace to use for the new element.
 * @param init A lambda used to configure the new element. By default, does nothing.
 *
 * @return Returns the newly created [Element].
 */
internal fun Node.appendElement(elementTagName: String, namespace: FeedNamespace? = null, init: Element.() -> Unit = {}): Element {
    val tagName = if (namespace != null) "${namespace.prefix}:$elementTagName" else elementTagName
    val element = getDocument().createElementNS(namespace?.uri, tagName)
    init(element)
    appendChild(element)
    return element
}

private fun Node.getDocument(): Document = when {
    this is Document -> this
    ownerDocument != null -> ownerDocument
    else -> throw IllegalStateException("Couldn't obtain document for node $this")
}

/**
 * Sets an attribute with the given [attributeName] to this [Element].
 * You can configure the new attribute by specifiying a lambda for [init].
 *
 * @param attributeName The name of the attribute to set.
 * @param namespace The namespace to use for the new element.
 * @param init A lambda used to configure the new element. By default, does nothing.
 *
 * @return Returns the newly created [Attr].
 */
internal fun Element.setAttributeWithNS(attributeName: String, namespace: FeedNamespace? = null, init: Attr.() -> Unit = {}): Attr {
    val name = if (namespace != null) "${namespace.prefix}:$attributeName" else attributeName
    val attr = ownerDocument.createAttributeNS(namespace?.uri, name)
    init(attr)
    setAttributeNodeNS(attr)
    return attr
}

/**
 * Appends a [tagName] element with text `yes` if the [value] is true.
 *
 * @param tagName The local name of the tag to append
 * @param value The value to use
 * @param namespace The namespace to use, if any
 */
internal fun Node.appendYesElementIfTrue(tagName: String, value: Boolean, namespace: FeedNamespace? = null) =
    appendElement(tagName, namespace) {
        val stringValue = value.asBooleanString(BooleanStringStyle.YES_NULL)
            ?: return@appendElement

        textContent = stringValue
    }

/**
 * Appends a [tagName] element with text `true` if the [value] is true, `false` otherwise.
 *
 * @param tagName The local name of the tag to append
 * @param value The value to use
 * @param namespace The namespace to use, if any
 */
internal fun Node.appendTrueFalseElement(tagName: String, value: Boolean, namespace: FeedNamespace? = null) =
    appendElement(tagName, namespace) {
        textContent = value.asBooleanString(BooleanStringStyle.TRUE_FALSE)
    }

/**
 * Appends a [tagName] element with the data from the provided [Person].
 *
 * @param tagName The local name of the tag to append
 * @param person The person instance to use
 * @param namespace The namespace to use, if any
 */
internal fun Element.appendPersonElement(tagName: String, person: Person, namespace: FeedNamespace? = null) {
    appendElement(tagName, namespace) {
        appendElement("name", namespace) { textContent = person.name }

        if (person.email != null) {
            appendElement("email", namespace) { textContent = person.email }
        }

        if (person.uri != null) {
            appendElement("uri", namespace) { textContent = person.uri }
        }
    }
}
