package io.hemin.wien.model

/**
 * An [RSS `<category>` tag][https://www.w3schools.com/XML/rss_tag_category_channel.asp]:
 *
 * ```xml
 * <category domain="my-domain">News</category>
 * ```
 *
 * @param name The name of the category.
 * @param domain A name or URL identifying a categorization taxonomy.
 */
data class RssCategory(val name: String, val domain: String? = null)
