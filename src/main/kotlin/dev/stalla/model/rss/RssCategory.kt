package dev.stalla.model.rss

import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.validating.ValidatingRssCategoryBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.atom.Atom.Factory.builder
import dev.stalla.model.atom.AtomPerson.Factory.builder
import dev.stalla.model.rss.RssCategory.Factory.builder

/**
 * An [RSS `<category>` tag](https://www.w3schools.com/XML/rss_tag_category_channel.asp):
 *
 * Direct instantiation from Java is discouraged. Use the [builder] method
 * to obtain a builder instance for expressive construction instead.
 *
 * ```xml
 * <category domain="my-domain">News</category>
 * ```
 *
 * @property name The name of the category.
 * @property domain A name or URL identifying a categorization taxonomy.
 *
 * @since 1.0.0
 */
public data class RssCategory(
    val name: String,
    val domain: String? = null
) {

    /** Provides a builder for the [RssCategory] class. */
    public companion object Factory : BuilderFactory<RssCategory, RssCategoryBuilder> {

        /** Returns a builder implementation for building [RssCategory] model instances. */
        @JvmStatic
        override fun builder(): RssCategoryBuilder = ValidatingRssCategoryBuilder()
    }
}
