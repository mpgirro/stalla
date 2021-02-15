package dev.stalla.model.googleplay

import dev.stalla.builder.GoogleplayCategoryBuilder
import dev.stalla.builder.validating.ValidatingGoogleplayCategoryBuilder
import dev.stalla.model.BuilderFactory

/**
 * An [Google Play `<category>` tag][https://support.google.com/googleplay/podcasts/answer/6260341#spt].
 *
 * @param name The name of the category.
 */
public sealed class GoogleplayCategory(public open val name: String) {

    public companion object Factory : BuilderFactory<GoogleplayCategory, GoogleplayCategoryBuilder> {

        /** Returns a builder implementation for building [GoogleplayCategory] model instances. */
        @JvmStatic
        override fun builder(): GoogleplayCategoryBuilder = ValidatingGoogleplayCategoryBuilder()
    }

    /**
     * A simple Google Play style category, without a nested subcategory:
     *
     * ```
     * <googleplay:category text="News" />
     * ```
     */
    public data class Simple(override val name: String) : GoogleplayCategory(name)

    /**
     * An Google Play style category that contains a nested subcategory:
     *
     * ```
     * <googleplay:category text="News">
     *     <googleplay:category text="Tech News" />
     * </googleplay:category>
     * ```
     *
     * @param subcategory The nested [Simple] subcategory.
     */
    public data class Nested(override val name: String, val subcategory: Simple) : GoogleplayCategory(name)
}
