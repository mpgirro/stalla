package dev.stalla.builder.fake

import dev.stalla.builder.GoogleplayCategoryBuilder
import dev.stalla.model.googleplay.GoogleplayCategory

internal class FakeGoogleplayCategoryBuilder : FakeBuilder<GoogleplayCategory>(), GoogleplayCategoryBuilder {

    var category: String? = null
    var subcategory: String? = null

    override fun category(category: String): GoogleplayCategoryBuilder = apply { this.category = category }

    override fun subcategory(subcategory: String?): GoogleplayCategoryBuilder = apply { this.subcategory = subcategory }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeGoogleplayCategoryBuilder) return false

        if (category != other.category) return false
        if (subcategory != other.subcategory) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category?.hashCode() ?: 0
        result = 31 * result + (subcategory?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakeGoogleplayCategoryBuilder(category=$category, subcategory=$subcategory)"
}
