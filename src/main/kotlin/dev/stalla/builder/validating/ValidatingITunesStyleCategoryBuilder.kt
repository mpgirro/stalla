package dev.stalla.builder.validating

import dev.stalla.builder.ITunesStyleCategoryBuilder
import dev.stalla.model.itunes.ItunesStyleCategory

internal class ValidatingITunesStyleCategoryBuilder : ITunesStyleCategoryBuilder {

    private lateinit var categoryValue: String

    private var subcategoryValue: String? = null

    override fun category(category: String): ITunesStyleCategoryBuilder = apply { this.categoryValue = category }

    override fun subcategory(subcategory: String?): ITunesStyleCategoryBuilder = apply { this.subcategoryValue = subcategory }

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
