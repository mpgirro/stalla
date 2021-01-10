package io.hemin.wien.dom

import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.model.ITunesStyleCategory
import io.hemin.wien.model.Person
import io.hemin.wien.model.RssCategory
import io.hemin.wien.model.RssImage
import io.hemin.wien.util.BooleanStringStyle
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.asBooleanString
import io.hemin.wien.util.isNeitherNullNorBlank
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node

/**
 * Appends a new `<namespace:image href="..."/>` with the given [namespace] to this [Element].
 * Currently the only supported [FeedNamespace] values are:
 *  * [FeedNamespace.ITUNES]
 *  * [FeedNamespace.GOOGLE_PLAY]
 *
 * @param image The image to represent with the new element.
 * @param namespace The namespace to use for the new element.
 */
internal fun Node.appendHrefOnlyImageElement(image: HrefOnlyImage, namespace: FeedNamespace): Element? {
    require(namespace == FeedNamespace.ITUNES || namespace == FeedNamespace.GOOGLE_PLAY) {
        "Only 'itunes:image' and 'googleplay:image' tags are supported, but the desired prefix was '${namespace.prefix}:'"
    }
    if (image.href.isBlank()) return null
    return appendElement("image", namespace) {
        setAttribute("href", image.href)
    }
}

/**
 * Appends an [RSS `<image>` tag][https://www.w3schools.com/XML/rss_tag_image.asp] to this [Element].
 *
 * @param image The image to represent with the new element.
 */
internal fun Node.appendRssImageElement(image: RssImage): Element = appendElement("image") {
    appendElement("title") { textContent = image.title }
    appendElement("link") { textContent = image.link }
    appendElement("url") { textContent = image.url }
    if (image.description != null) appendElement("description") { textContent = image.description }
    if (image.height != null) appendElement("height") { textContent = image.height.toString() }
    if (image.width != null) appendElement("width") { textContent = image.width.toString() }
}

/**
 * Appends a new [Element] with the given [tag name][elementTagName] to this [Node].
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
internal fun Node.appendPersonElement(tagName: String, person: Person, namespace: FeedNamespace? = null) {
    if (person.name.isBlank()) return

    appendElement(tagName, namespace) {
        appendElement("name", namespace) { textContent = person.name }

        if (person.email.isNeitherNullNorBlank()) {
            appendElement("email", namespace) { textContent = person.email }
        }

        if (person.uri.isNeitherNullNorBlank()) {
            appendElement("uri", namespace) { textContent = person.uri }
        }
    }
}

/**
 * Appends iTunes-Style <ns:category> tags with the data from the provided [categories].
 *
 * @param categories The [categories][ITunesStyleCategory] to append.
 * @param namespace The namespace to use, if any.
 */
internal fun Node.appendITunesCategoryElements(categories: List<ITunesStyleCategory>, namespace: FeedNamespace? = null) {
    for (category in categories) {
        if (category.name.isBlank()) continue
        appendElement("category", namespace) {
            setAttribute("text", category.name)

            if (category is ITunesStyleCategory.Nested && category.subcategory.name.isNotBlank()) {
                appendElement("category", namespace) {
                    setAttribute("text", category.subcategory.name)
                }
            }
        }
    }
}

/**
 * Appends RSS <category> tags with the data from the provided [categories].
 *
 * @param categories The [categories][RssCategory] to append.
 * @param namespace The namespace to use, if any.
 */
internal fun Node.appendRssCategoryElements(categories: List<RssCategory>, namespace: FeedNamespace? = null) {
    for (category in categories) {
        appendElement("category", namespace) {
            textContent = category.name
            setAttribute("domain", category.domain)
        }
    }
}
