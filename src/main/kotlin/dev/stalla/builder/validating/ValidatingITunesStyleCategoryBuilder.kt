package dev.stalla.builder.validating

import dev.stalla.builder.ITunesStyleCategoryBuilder
import dev.stalla.model.ITunesStyleCategory

internal class ValidatingITunesStyleCategoryBuilder : ITunesStyleCategoryBuilder {

    private lateinit var categoryValue: String

    private var subcategoryValue: String? = null

    override fun category(category: String): ITunesStyleCategoryBuilder = apply { this.categoryValue = category }

    override fun subcategory(subcategory: String?): ITunesStyleCategoryBuilder = apply { this.subcategoryValue = subcategory }

    override val hasEnoughDataToBuild: Boolean
        get() = ::categoryValue.isInitialized

    override fun build(): ITunesStyleCategory? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val subcategory = subcategoryValue
        return if (subcategory == null) {
            ITunesStyleCategory.Simple(categoryValue)
        } else {
            ITunesStyleCategory.Nested(categoryValue, ITunesStyleCategory.Simple(subcategory))
        }
    }
}
