package dev.stalla.builder.validating

import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.model.rss.RssCategory
import dev.stalla.util.InternalAPI2

@InternalAPI2
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

        return RssCategory(
            name = categoryValue,
            domain = domain
        )
    }
}
