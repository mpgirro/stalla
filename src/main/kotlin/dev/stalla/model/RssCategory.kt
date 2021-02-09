package dev.stalla.model

import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.validating.ValidatingRssCategoryBuilder

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
public data class RssCategory(val name: String, val domain: String? = null) {

    public companion object Factory : BuilderFactory<RssCategory, RssCategoryBuilder> {

        /** Returns a builder implementation for building [RssCategory] model instances. */
        @JvmStatic
        override fun builder(): RssCategoryBuilder = ValidatingRssCategoryBuilder()
    }
}
