package dev.stalla.builder.fake

import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.model.itunes.ItunesStyleCategory

internal class FakeItunesStyleCategoryBuilder : FakeBuilder<ItunesStyleCategory>(), ItunesStyleCategoryBuilder {

    var category: String? = null
    var subcategory: String? = null

    override fun category(category: String): ItunesStyleCategoryBuilder = apply { this.category = category }

    override fun subcategory(subcategory: String?): ItunesStyleCategoryBuilder = apply { this.subcategory = subcategory }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeItunesStyleCategoryBuilder) return false

        if (category != other.category) return false
        if (subcategory != other.subcategory) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category?.hashCode() ?: 0
        result = 31 * result + (subcategory?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakeITunesCategoryBuilder(category=$category, subcategory=$subcategory)"
}
