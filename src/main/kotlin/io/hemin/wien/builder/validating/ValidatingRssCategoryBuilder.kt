package io.hemin.wien.builder.validating

import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.model.Category

internal class ValidatingRssCategoryBuilder : RssCategoryBuilder {

    private lateinit var categoryValue: String

    private var domain: String? = null

    override fun category(category: String): RssCategoryBuilder = apply { this.categoryValue = category }

    override fun domain(domain: String?): RssCategoryBuilder = apply { this.domain = domain }

    override fun build(): Category.Rss? {
        if (!::categoryValue.isInitialized) {
            return null
        }

        return Category.Rss(categoryValue, domain)
    }
}
