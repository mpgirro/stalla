package io.hemin.wien.builder

import io.hemin.wien.model.Category

internal interface RssCategoryBuilder : Builder<Category.Rss> {

    /** Set the category value. */
    fun category(category: String): RssCategoryBuilder

    /** Set the domain value. */
    fun domain(domain: String?): RssCategoryBuilder
}
