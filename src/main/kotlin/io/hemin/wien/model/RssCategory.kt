package io.hemin.wien.model

import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.validating.ValidatingRssCategoryBuilder

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
data class RssCategory(val name: String, val domain: String? = null) {
    companion object Factory : BuilderFactory<RssCategory, RssCategoryBuilder> {
        @JvmStatic override fun builder(): RssCategoryBuilder = ValidatingRssCategoryBuilder()
    }
}
