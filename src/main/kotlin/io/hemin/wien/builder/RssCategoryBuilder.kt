package io.hemin.wien.builder

import io.hemin.wien.model.RssCategory

internal interface RssCategoryBuilder : Builder<RssCategory> {

    /** Set the category value. */
    fun category(category: String): RssCategoryBuilder

    /** Set the domain value. */
    fun domain(domain: String?): RssCategoryBuilder
}
