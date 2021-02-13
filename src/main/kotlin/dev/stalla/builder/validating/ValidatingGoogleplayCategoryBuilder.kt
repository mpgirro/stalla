package dev.stalla.builder.validating

import dev.stalla.builder.GoogleplayCategoryBuilder
import dev.stalla.model.googleplay.GoogleplayCategory

internal class ValidatingGoogleplayCategoryBuilder : GoogleplayCategoryBuilder {

    private lateinit var categoryValue: String

    private var subcategoryValue: String? = null

    override fun category(category: String): GoogleplayCategoryBuilder = apply { this.categoryValue = category }

    override fun subcategory(subcategory: String?): GoogleplayCategoryBuilder = apply { this.subcategoryValue = subcategory }

    override val hasEnoughDataToBuild: Boolean
        get() = ::categoryValue.isInitialized

    override fun build(): GoogleplayCategory? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val subcategory = subcategoryValue
        return if (subcategory == null) {
            GoogleplayCategory.Simple(categoryValue)
        } else {
            GoogleplayCategory.Nested(categoryValue, GoogleplayCategory.Simple(subcategory))
        }
    }
}
