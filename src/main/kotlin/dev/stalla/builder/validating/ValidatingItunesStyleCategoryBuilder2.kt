package dev.stalla.builder.validating

import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.model.itunes.ItunesStyleCategory2

internal class ValidatingItunesStyleCategoryBuilder2 : ItunesStyleCategoryBuilder2 {

    private lateinit var categoryValue: String

    private var subcategoryValue: String? = null

    override fun category(category: String): ItunesStyleCategoryBuilder2 = apply { this.categoryValue = category }

    override fun subcategory(subcategory: String?): ItunesStyleCategoryBuilder2 = apply { this.subcategoryValue = subcategory }

    override val hasEnoughDataToBuild: Boolean
        get() = ::categoryValue.isInitialized

    override fun build(): ItunesStyleCategory2? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val subcategory = subcategoryValue
        return if (subcategory == null) {
            ItunesStyleCategory2.Simple(categoryValue)
        } else {
            ItunesStyleCategory2.Nested(categoryValue, ItunesStyleCategory2.Simple(subcategory))
        }
    }
}
