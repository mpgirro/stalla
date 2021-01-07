package io.hemin.wien.builder.validating

import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.model.RssCategory

internal class ValidatingRssCategoryBuilder : RssCategoryBuilder {

    private lateinit var categoryValue: String

    private var domain: String? = null

    override fun category(category: String): RssCategoryBuilder = apply { this.categoryValue = category }

    override fun domain(domain: String?): RssCategoryBuilder = apply { this.domain = domain }

    override val hasEnoughDataToBuild: Boolean
        get() = ::categoryValue.isInitialized

    override fun build(): RssCategory? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return RssCategory(categoryValue, domain)
    }
}
