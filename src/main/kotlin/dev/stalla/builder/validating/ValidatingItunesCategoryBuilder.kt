package dev.stalla.builder.validating

import dev.stalla.builder.ItunesCategoryBuilder
import dev.stalla.model.itunes.ItunesCategory

internal class ValidatingItunesCategoryBuilder : ItunesCategoryBuilder {

    private lateinit var categoryValue: String

    private var subcategoryValue: String? = null

    override fun category(category: String): ItunesCategoryBuilder = apply { this.categoryValue = category }

    override fun subcategory(subcategory: String?): ItunesCategoryBuilder = apply { this.subcategoryValue = subcategory }

    override val hasEnoughDataToBuild: Boolean
        get() = ::categoryValue.isInitialized

    override fun build(): ItunesCategory? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val subcategory = subcategoryValue
        return if (subcategory == null) {
            ItunesCategory.Simple(categoryValue)
        } else {
            ItunesCategory.Nested(categoryValue, ItunesCategory.Simple(subcategory))
        }
    }
}
