package dev.stalla.model

import dev.stalla.builder.ITunesStyleCategoryBuilder
import dev.stalla.builder.validating.ValidatingITunesStyleCategoryBuilder

/**
 * An [iTunes-style `<category>` tag][https://help.apple.com/itc/podcasts_connect/#/itcb54353390].
 * The same format is also used for Google Play <category> tags, just with a different namespace.
 *
 * @param name The name of the category.
 */
public sealed class ITunesStyleCategory(public open val name: String) {

    public companion object Factory : BuilderFactory<ITunesStyleCategory, ITunesStyleCategoryBuilder> {

        /** Returns a builder implementation for building [ITunesStyleCategory] model instances. */
        @JvmStatic
        override fun builder(): ITunesStyleCategoryBuilder = ValidatingITunesStyleCategoryBuilder()
    }

    /**
     * A simple iTunes-style category, without a nested subcategory:
     *
     * ```
     * <itunes:category text="News" />
     * ```
     */
    public data class Simple(override val name: String) : ITunesStyleCategory(name)

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
    public data class Nested(override val name: String, val subcategory: Simple) : ITunesStyleCategory(name)
}
