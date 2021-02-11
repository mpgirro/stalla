package dev.stalla.builder.validating

import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.model.itunes.ItunesStyleCategory

internal class ValidatingItunesStyleCategoryBuilder : ItunesStyleCategoryBuilder {

    private lateinit var categoryValue: String

    private var subcategoryValue: String? = null

    override fun category(category: String): ItunesStyleCategoryBuilder = apply { this.categoryValue = category }

    override fun subcategory(subcategory: String?): ItunesStyleCategoryBuilder = apply { this.subcategoryValue = subcategory }

    override val hasEnoughDataToBuild: Boolean
        get() = ::categoryValue.isInitialized

    override fun build(): ItunesStyleCategory? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val subcategory = subcategoryValue
        return if (subcategory == null) {
            ItunesStyleCategory.Simple(categoryValue)
        } else {
            ItunesStyleCategory.Nested(categoryValue, ItunesStyleCategory.Simple(subcategory))
        }
    }
}
