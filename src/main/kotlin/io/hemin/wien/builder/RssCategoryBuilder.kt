package io.hemin.wien.builder

import io.hemin.wien.model.RssCategory
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [RssCategory] instances. */
interface RssCategoryBuilder : Builder<RssCategory> {

    /** Set the category value. */
    fun category(category: String): RssCategoryBuilder

    /** Set the domain value. */
    fun domain(domain: String?): RssCategoryBuilder

    override fun from(model: RssCategory?): RssCategoryBuilder = whenNotNull(model) { category ->
        category(category.name)
        domain(category.domain)
    }
}
