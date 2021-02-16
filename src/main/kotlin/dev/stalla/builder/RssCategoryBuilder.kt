package dev.stalla.builder

import dev.stalla.model.rss.RssCategory
import dev.stalla.util.whenNotNull

/** Builder for constructing [RssCategory] instances. */
public interface RssCategoryBuilder : Builder<RssCategory> {

    /** Set the category value. */
    public fun category(category: String): RssCategoryBuilder

    /** Set the domain value. */
    public fun domain(domain: String?): RssCategoryBuilder

    override fun applyFrom(prototype: RssCategory?): RssCategoryBuilder = whenNotNull(prototype) { category ->
        category(category.name)
        domain(category.domain)
    }
}
