package io.hemin.wien.builder.validating

import io.hemin.wien.builder.ITunesCategoryBuilder
import io.hemin.wien.model.Category

internal class ValidatingITunesCategoryBuilder : ITunesCategoryBuilder {

    private lateinit var categoryValue: String

    private var subcategoryValue: String? = null

    override fun category(category: String): ITunesCategoryBuilder = apply { this.categoryValue = category }

    override fun subcategory(subcategory: String?): ITunesCategoryBuilder = apply { this.subcategoryValue = subcategory }

    override fun build(): Category.ITunes? {
        if (!::categoryValue.isInitialized) {
            return null
        }

        val subcategory = subcategoryValue
        return if (subcategory == null) {
            Category.ITunes.Simple(categoryValue)
        } else {
            Category.ITunes.Nested(categoryValue, Category.ITunes.Simple(subcategory))
        }
    }
}
