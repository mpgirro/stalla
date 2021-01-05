package io.hemin.wien.model

/**
 * A `<category>` tag. All [Category] types have a [category] name, but some may
 * contain additional data.
 *
 * @param category The name of the category.
 */
sealed class Category(open val category: String) {

    /**
     * An [RSS `<category>` tag][https://www.w3schools.com/XML/rss_tag_category_channel.asp]:
     *
     * ```xml
     * <category domain="my-domain">News</category>
     * ```
     *
     * @param domain A name or URL identifying a categorization taxonomy.
     */
    data class Rss(override val category: String, val domain: String? = null) : Category(category)

    /**
     * An [iTunes-style `<category>` tag][https://help.apple.com/itc/podcasts_connect/#/itcb54353390].
     * The same format is also used for Google Play <category> tags, just with a different namespace.
     */
    sealed class ITunes(override val category: String) : Category(category) {

        /**
         * A simple iTunes-style category, without a nested subcategory:
         *
         * ```
         * <itunes:category text="News" />
         * ```
         */
        data class Simple(override val category: String) : ITunes(category)

        /**
         * An iTunes-style category that contains a nested subcategory:
         *
         * ```
         * <itunes:category text="News">
         *     <itunes:category text="Tech News" />
         * </itunes:category>
         * ```
         *
         * @param nested The nested [Simple] category.
         */
        data class Nested(override val category: String, val nested: Simple) : ITunes(category)
    }
}
