package dev.stalla.dom

import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.RssImageBuilder
import dev.stalla.model.MediaType
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.parser.DateParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.FeedNamespace.Companion.matches
import dev.stalla.util.InternalAPI
import dev.stalla.util.isValidLocale
import dev.stalla.util.trimmedOrNullIfBlank
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
@InternalAPI
internal fun Node.textOrNull(): String? = textContent.trimmedOrNullIfBlank()

/**
 * Extracts the [Node.getTextContent] of a DOM node, and tries to parse it as a boolean.
 * If the textContent cannot be parsed, returns `null`.
 *
 * @see parseAsBooleanOrNull
 * @return The logical interpretation of the DOM node's text content as boolean, or `null`.
 */
@InternalAPI
internal fun Node.textAsBooleanOrNull(): Boolean? = textOrNull().parseAsBooleanOrNull()

/**
 * Interprets a string content as a boolean. If the string value cannot be parsed, returns `null`.
 * Supports values of `yes`/`no`, or `true`/`false`, case insensitive.
 *
 * @return The logical interpretation of the string parameter, or `null`.
 */
@InternalAPI
internal fun String?.parseAsBooleanOrNull(): Boolean? =
    when (this.trimmedOrNullIfBlank()?.toLowerCase(Locale.ROOT)) {
        "true", "yes" -> true
        "false", "no" -> false
        else -> null
    }

/**
 * Extracts the text content of a DOM node, and transforms it to an Int instance.
 *
 * @return The DOM node content as an [Int], or `null` if conversion failed.
 */
@InternalAPI
internal fun Node.parseAsInt(): Int? = textOrNull()?.toIntOrNull()

/**
 * Extracts the text content of a DOM node, and transforms it to an Int instance.
 *
 * @return The DOM node content as an [Double], or `null` if conversion failed.
 */
@InternalAPI
internal fun Node.parseAsDouble(): Double? = textOrNull()?.toDoubleOrNull()

/**
 * Extracts the text content of a DOM node, and parses it as a [TemporalAccessor] instance
 * if possible.
 *
 * @return The DOM node content as a [TemporalAccessor], or `null` if parsing failed.
 */
@InternalAPI
internal fun Node.parseAsTemporalAccessor(): TemporalAccessor? = DateParser.parse(textOrNull())

/**
 * Extracts the text content of a DOM node, and parses it as a [Locale] instance
 * if possible.
 *
 * @return The DOM node content as a [Locale], or `null` if parsing failed.
 */
@InternalAPI
internal fun Node.parseAsLocaleOrNull(): Locale? = textOrNull()?.let { rawLocale ->
    val locale = Locale.forLanguageTag(rawLocale)
    return if (locale.isValidLocale()) locale else null
}

/**
 * Interprets a string content as a [MediaType]. If the string value cannot be parsed, returns `null`.
 *
 * @return The [MediaType] interpretation of the string parameter, or `null`.
 */
@InternalAPI
@Suppress("SwallowedException")
internal fun String?.parseAsMediaTypeOrNull(): MediaType? = this.trimmedOrNullIfBlank()
    ?.let { rawType ->
        try {
            MediaType.of(rawType)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

/**
 * Parses the node contents as an [RssImageBuilder] if possible, interpreting it as an RSS
 * `<image>` tag, then populates the [imageBuilder] with the parsed data.
 *
 * @see toHrefOnlyImageBuilder
 *
 * @param imageBuilder An empty [RssImageBuilder] instance to initialise with the node's
 * contents.
 * @param namespace The [FeedNamespace] to ensure the child nodes have.
 *
 * @return The [imageBuilder] populated with the DOM node contents.
 */
@InternalAPI
@Suppress("ComplexMethod")
internal fun Node.toRssImageBuilder(imageBuilder: RssImageBuilder, namespace: FeedNamespace? = null): RssImageBuilder {
    for (node in childNodes.asListOfNodes()) {
        if (!namespace.matches(node.namespaceURI)) continue

        when (node.localName) {
            "description" -> imageBuilder.description(node.textOrNull())
            "height" -> imageBuilder.height(node.parseAsInt())
            "link" -> node.textOrNull()?.let(imageBuilder::link)
            "title" -> node.textOrNull()?.let(imageBuilder::title)
            "url" -> node.textOrNull()?.let(imageBuilder::url)
            "width" -> imageBuilder.width(node.parseAsInt())
        }
    }
    return imageBuilder
}

/**
 * Parses the node contents as an [HrefOnlyImageBuilder] if possible, interpreting it as a
 * `<namespace:image href="..."/>` tag then populates the [imageBuilder] with the parsed data.
 *
 * @see toRssImageBuilder
 *
 * @param imageBuilder An empty [HrefOnlyImageBuilder] instance to initialise with the node's
 * contents.
 *
 * @return The [imageBuilder] populated with the DOM node contents.
 */
@InternalAPI
internal fun Node.toHrefOnlyImageBuilder(imageBuilder: HrefOnlyImageBuilder): HrefOnlyImageBuilder {
    val href: String? = getAttributeValueByName("href")
    if (!href.isNullOrBlank()) imageBuilder.href(href)
    return imageBuilder
}

/**
 * Parses the node contents into a [AtomPersonBuilder] if possible, ensuring the child nodes
 * have the specified [namespace], then populates the [personBuilder] with the parsed data.
 *
 * @param personBuilder An empty [AtomPersonBuilder] instance to initialise with the node's
 * contents.
 * @param namespace The [FeedNamespace] to ensure the child nodes have.
 *
 * @return The [personBuilder] populated with the DOM node contents.
 */
@InternalAPI
@Suppress("LoopWithTooManyJumpStatements")
internal fun Node.toAtomPersonBuilder(
    personBuilder: AtomPersonBuilder,
    namespace: FeedNamespace? = null
): AtomPersonBuilder {
    for (child in childNodes.asListOfNodes()) {
        if (child !is Element) continue
        if (!namespace.matches(child.namespaceURI)) continue
        val value: String? = child.textOrNull()

        when (child.localName) {
            "name" -> value?.let(personBuilder::name)
            "email" -> personBuilder.email(value)
            "uri" -> personBuilder.uri(value)
        }
    }
    return personBuilder
}

/**
 * Parses the node contents into a [RssCategoryBuilder] if possible, then populates the
 * [categoryBuilder] with the parsed data.
 *
 * @param categoryBuilder An empty [RssCategoryBuilder] instance to initialise with the node's
 * contents.
 *
 * @return The [categoryBuilder] populated with the DOM node contents.
 */
@InternalAPI
internal fun Node.toRssCategoryBuilder(categoryBuilder: RssCategoryBuilder): RssCategoryBuilder? {
    val categoryValue = textOrNull() ?: return null
    return categoryBuilder.category(categoryValue)
        .domain(getAttributeValueByName("domain"))
}

/**
 * Parses the node contents into a [ItunesCategory] if possible, ensuring the child nodes
 * have the specified [namespace], then populates the [ItunesCategory] with the parsed data.
 *
 * Only accepts category entries and nested hierarchies as they are defined in the
 * [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
 *
 * @param namespace The [FeedNamespace] to ensure the child nodes have.
 *
 * @return The [ItunesCategory] populated with the DOM node contents.
 */
@InternalAPI
internal fun Node.toItunesCategory(namespace: FeedNamespace? = null): ItunesCategory? {
    val categoryValue = getAttributeValueByName("text")?.trim() ?: return null
    val category = ItunesCategory.of(categoryValue) ?: return null

    val subcategoryElement = findElementByName("category", namespace) ?: return category
    val subcategoryValue = subcategoryElement.getAttributeValueByName("text")?.trim()
    return ItunesCategory.of(subcategoryValue) ?: return category
}

/**
 * Parses the node contents into a [GoogleplayCategory] if possible.
 *
 * @return The [GoogleplayCategory] populated with the DOM node contents.
 */
@InternalAPI
internal fun Node.toGoogleplayCategory(): GoogleplayCategory? {
    val categoryValue = getAttributeValueByName("text")?.trim() ?: return null
    return GoogleplayCategory.of(categoryValue)
}
