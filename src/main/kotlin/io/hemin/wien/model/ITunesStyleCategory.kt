package io.hemin.wien.model

/**
 * An [iTunes-style `<category>` tag][https://help.apple.com/itc/podcasts_connect/#/itcb54353390].
 * The same format is also used for Google Play <category> tags, just with a different namespace.
 *
 * @param name The name of the category.
 */
sealed class ITunesStyleCategory(open val name: String) {

    /**
     * A simple iTunes-style category, without a nested subcategory:
     *
     * ```
     * <itunes:category text="News" />
     * ```
     */
    data class Simple(override val name: String) : ITunesStyleCategory(name)

    /**
     * An iTunes-style category that contains a nested subcategory:
     *
     * ```
     * <itunes:category text="News">
     *     <itunes:category text="Tech News" />
     * </itunes:category>
     * ```
     *
     * @param subcategory The nested [Simple] subcategory.
     */
    data class Nested(override val name: String, val subcategory: Simple) : ITunesStyleCategory(name)
}
