package io.hemin.wien.builder

import io.hemin.wien.parser.DateParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.asListOfAttrs
import io.hemin.wien.util.asListOfNodes
import io.hemin.wien.util.getAttributeValueByName
import io.hemin.wien.util.trimmedOrNullIfBlank
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.time.temporal.TemporalAccessor
import java.util.Locale

/**
 * Extracts the [Node.getTextContent] of a DOM node. Trims whitespace at the beginning and the end.
 * Returns `null` if the text is blank.
 *
 * @return The content of the DOM node in string representation, or null.
 */
internal fun Node.textOrNull(): String? = textContent.trimmedOrNullIfBlank()

/**
 * Extracts the [Node.getTextContent] of a DOM node, and tries to parse it as a boolean.
 * If the textContent cannot be parsed, returns `null`.
 *
 * @see parseAsBooleanOrNull
 * @return The logical interpretation of the DOM node's text content as boolean, or `null`.
 */
internal fun Node.textAsBooleanOrNull(): Boolean? = textOrNull().parseAsBooleanOrNull()

/**
 * Interprets a string content as a boolean. If the string value cannot be parsed, returns `null`.
 * Supports values of `yes`/`no`, or `true`/`false`, case insensitive.
 *
 * @return The logical interpretation of the string parameter, or `null`.
 */
internal fun String?.parseAsBooleanOrNull() = when (this?.toLowerCase(Locale.ROOT)) {
    "true", "yes" -> true
    "false", "no" -> false
    else -> null
}

/**
 * Extracts the text content of a DOM node, and transforms it to an Int instance.
 *
 * @return The DOM node content as an [Int], or `null` if conversion failed.
 */
internal fun Node.parseAsInt(): Int? = textOrNull()?.toIntOrNull()

/**
 * Extracts the text content of a DOM node, and parses it as a [TemporalAccessor] instance
 * if possible.
 *
 * @return The DOM node content as a [TemporalAccessor], or `null` if parsing failed.
 */
internal fun Node.parseAsTemporalAccessor(): TemporalAccessor? = DateParser.parse(textOrNull())

/**
 * Parses the node contents as an [ImageBuilder] if possible, interpreting it as an RSS
 * `<image>` tag.
 *
 * @see toHrefOnlyImageBuilder
 *
 * @param imageBuilder An empty [ImageBuilder] instance to initialise with the node's
 * contents.
 * @param namespace The [FeedNamespace] to ensure the child nodes have.
 *
 * @return The DOM node content as an [ImageBuilder], or `null` if parsing failed.
 */
internal fun Node.toRssImageBuilder(imageBuilder: ImageBuilder, namespace: FeedNamespace? = null): ImageBuilder {
    for (node in childNodes.asListOfNodes()) {
        if (node.namespaceURI != namespace?.uri) continue

        when (node.localName) {
            "description" -> imageBuilder.description(node.textOrNull())
            "height" -> imageBuilder.height(node.parseAsInt())
            "link" -> imageBuilder.link(node.textOrNull())
            "title" -> imageBuilder.title(node.textOrNull())
            "url" -> {
                val url = node.textOrNull() ?: continue
                imageBuilder.url(url)
            }
            "width" -> imageBuilder.width(node.parseAsInt())
        }
    }
    return imageBuilder
}

/**
 * Parses the node contents as an [ImageBuilder] if possible, interpreting it as a
 * `<namespace:image href="..."/>` tag.
 *
 * @see toRssImageBuilder
 *
 * @param imageBuilder An empty [ImageBuilder] instance to initialise with the node's
 * contents.
 * @param namespace The [FeedNamespace] to ensure the `href` attribute has.
 *
 * @return The DOM node content as an [ImageBuilder], or `null` if parsing failed.
 */
internal fun Node.toHrefOnlyImageBuilder(imageBuilder: ImageBuilder, namespace: FeedNamespace? = null): ImageBuilder {
    val url: String? = getAttributeValueByName("href", namespace)
    if (!url.isNullOrBlank()) imageBuilder.url(url)
    return imageBuilder
}

/**
 * Parses the node contents into a [PersonBuilder] if possible, ensuring the child nodes
 * have the specified [namespace].
 *
 * @param personBuilder An empty [PersonBuilder] instance to initialise with the node's
 * contents.
 * @param namespace The [FeedNamespace] to ensure the child nodes have.
 *
 * @return The DOM node content as a [PersonBuilder], or `null` if parsing failed.
 */
internal fun Node.toPersonBuilder(personBuilder: PersonBuilder, namespace: FeedNamespace? = null): PersonBuilder {
    for (child in childNodes.asListOfNodes()) {
        if (child !is Element) continue
        if (child.namespaceURI != namespace?.uri) continue
        val value: String? = child.textOrNull()

        when (child.localName) {
            "name" -> if (value != null) personBuilder.name(value)
            "email" -> personBuilder.email(value)
            "uri" -> personBuilder.uri(value)
        }
    }
    return personBuilder
}
