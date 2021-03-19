package dev.stalla.dom

import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.atom.AtomPerson
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.ItunesOwner
import dev.stalla.model.rss.RssCategory
import dev.stalla.model.rss.RssImage
import dev.stalla.util.BooleanStringStyle
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI
import dev.stalla.util.asBooleanString
import dev.stalla.util.isNeitherNullNorBlank
import org.w3c.dom.Attr
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Appends a new `<namespace:image href="..."/>` with the given [namespace] to this [Element].
 * Currently the only supported [FeedNamespace] values are:
 *  * [FeedNamespace.ITUNES]
 *  * [FeedNamespace.GOOGLE_PLAY]
 *
 * @param image The image to represent with the new element.
 * @param namespace The namespace to use for the new element.
 */
@InternalAPI
internal fun Node.appendHrefOnlyImageElement(image: HrefOnlyImage, namespace: FeedNamespace): Element? {
    require(namespace == FeedNamespace.ITUNES || namespace == FeedNamespace.GOOGLE_PLAY) {
        "Only 'itunes:image' and 'googleplay:image' tags are supported, " +
            "but the desired prefix was '${namespace.prefix}:'"
    }
    if (image.href.isBlank()) return null
    return appendElement("image", namespace) {
        setAttribute("href", image.href.trim())
    }
}

/**
 * Appends an [RSS `<image>` tag](https://www.w3schools.com/XML/rss_tag_image.asp) to this [Element].
 *
 * @param image The image to represent with the new element.
 */
@InternalAPI
internal fun Node.appendRssImageElement(image: RssImage): Element? {
    if (image.url.isBlank()) return null
    return appendElement("image") {
        appendElement("url") { textContent = image.url.trim() }
        if (image.title.isNotBlank()) {
            appendElement("title") { textContent = image.title.trim() }
        }
        if (image.link.isNotBlank()) {
            appendElement("link") { textContent = image.link.trim() }
        }
        if (image.description.isNeitherNullNorBlank()) {
            appendElement("description") { textContent = image.description.trim() }
        }
        if (image.height != null) {
            appendElement("height") { textContent = image.height.toString() }
        }
        if (image.width != null) {
            appendElement("width") { textContent = image.width.toString() }
        }
    }
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
@InternalAPI
internal fun Node.appendElement(
    elementTagName: String,
    namespace: FeedNamespace? = null,
    init: Element.() -> Unit = {}
): Element {
    contract {
        callsInPlace(init, InvocationKind.EXACTLY_ONCE)
    }
    val tagName = if (namespace != null) "${namespace.prefix}:$elementTagName" else elementTagName
    val element = getDocument().createElementNS(namespace?.uri, tagName)
    init(element)
    appendChild(element)
    return element
}

private fun Node.getDocument(): Document = when {
    this is Document -> this
    ownerDocument != null -> ownerDocument
    else -> error("Couldn't obtain document for node $this")
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
@InternalAPI
internal fun Element.setAttributeWithNS(
    attributeName: String,
    namespace: FeedNamespace? = null,
    init: Attr.() -> Unit = {}
): Attr {
    contract {
        callsInPlace(init, InvocationKind.EXACTLY_ONCE)
    }
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
@InternalAPI
internal fun Node.appendYesElementIfTrue(tagName: String, value: Boolean, namespace: FeedNamespace? = null): Element? {
    val stringValue = value.asBooleanString(BooleanStringStyle.YES_NULL)
        ?: return null

    return appendElement(tagName, namespace) {
        textContent = stringValue
    }
}

/**
 * Appends a [tagName] element with text `true` if the [value] is true, `false` otherwise.
 *
 * @param tagName The local name of the tag to append
 * @param value The value to use
 * @param namespace The namespace to use, if any
 */
@InternalAPI
internal fun Node.appendTrueFalseElement(tagName: String, value: Boolean, namespace: FeedNamespace? = null) =
    appendElement(tagName, namespace) {
        textContent = value.asBooleanString(BooleanStringStyle.TRUE_FALSE)
    }

/**
 * Appends a [tagName] element with text `yes` if the [value] is true, `no` otherwise.
 *
 * @param tagName The local name of the tag to append
 * @param value The value to use
 * @param namespace The namespace to use, if any
 */
@InternalAPI
internal fun Node.appendYesNoElement(tagName: String, value: Boolean, namespace: FeedNamespace? = null) =
    appendElement(tagName, namespace) {
        textContent = value.asBooleanString(BooleanStringStyle.YES_NO)
    }

/**
 * Appends a [tagName] element with the data from the provided [AtomPerson].
 *
 * @param tagName The local name of the tag to append
 * @param person The person instance to use
 * @param namespace The namespace to use, if any
 */
@InternalAPI
internal fun Node.appendAtomPersonElement(tagName: String, person: AtomPerson, namespace: FeedNamespace? = null) {
    if (person.name.isBlank()) return

    appendElement(tagName, namespace) {
        appendElement("name", namespace) { textContent = person.name.trim() }

        if (person.email.isNeitherNullNorBlank()) {
            appendElement("email", namespace) { textContent = person.email.trim() }
        }

        if (person.uri.isNeitherNullNorBlank()) {
            appendElement("uri", namespace) { textContent = person.uri.trim() }
        }
    }
}

/**
 * Appends a [tagName] element with the data from the provided [ItunesOwner].
 *
 * @param tagName The local name of the tag to append
 * @param owner The owner instance to use
 * @param namespace The namespace to use, if any
 */
@InternalAPI
internal fun Node.appendItunesOwnerElement(tagName: String, owner: ItunesOwner, namespace: FeedNamespace? = null) {
    if (owner.name.isBlank()) return

    appendElement(tagName, namespace) {
        appendElement("name", namespace) { textContent = owner.name.trim() }

        appendElement("email", namespace) { textContent = owner.email.trim() }
    }
}

/**
 * Appends iTunes-Style `<ns:category>` tags with the data from the provided [categories].
 *
 * @param categories The [categories][ItunesCategory] to append.
 * @param namespace The namespace to use, if any.
 */
@InternalAPI
internal fun Node.appendItunesStyleCategoryElements(
    categories: List<ItunesCategory>,
    namespace: FeedNamespace? = null
) {
    for (category in categories) {
        if (category.type.isBlank()) continue

        when (category) {
            is ItunesCategory.Simple -> {
                appendElement("category", namespace) {
                    setAttribute("text", category.type.trim())
                }
            }
            is ItunesCategory.Nested -> {
                appendElement("category", namespace) {
                    // Write parent category
                    setAttribute("text", category.parent.type.trim())

                    // Write sub-category
                    appendElement("category", namespace) {
                        setAttribute("text", category.type.trim())
                    }
                }
            }
        }
    }
}

/**
 * Appends Google Play style `<googleplay:category>` tags with the data from the provided [categories].
 *
 * @param categories The [categories][GoogleplayCategory] to append.
 * @param namespace The namespace to use, if any.
 */
@InternalAPI
internal fun Node.appendGoogleplayCategoryElements(
    categories: List<GoogleplayCategory>,
    namespace: FeedNamespace? = null
) {
    for (category in categories) {
        if (category.name.isBlank()) continue
        appendElement("category", namespace) {
            setAttribute("text", category.type.trim())
        }
    }
}

/**
 * Appends RSS `<category>` tags with the data from the provided [categories].
 *
 * @param categories The [categories][RssCategory] to append.
 * @param namespace The namespace to use, if any.
 */
@InternalAPI
internal fun Node.appendRssCategoryElements(categories: List<RssCategory>, namespace: FeedNamespace? = null) {
    for (category in categories) {
        if (category.name.isBlank()) continue
        appendElement("category", namespace) {
            textContent = category.name.trim()
            if (category.domain.isNeitherNullNorBlank()) {
                setAttribute("domain", category.domain.trim())
            }
        }
    }
}
