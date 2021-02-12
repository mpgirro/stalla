package dev.stalla.builder.fake

import dev.stalla.builder.ItunesCategoryBuilder
import dev.stalla.model.itunes.ItunesCategory

internal class FakeItunesCategoryBuilder : FakeBuilder<ItunesCategory>(), ItunesCategoryBuilder {

    var category: String? = null
    var subcategory: String? = null

    override fun category(category: String): ItunesCategoryBuilder = apply { this.category = category }

    override fun subcategory(subcategory: String?): ItunesCategoryBuilder = apply { this.subcategory = subcategory }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeItunesCategoryBuilder) return false

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
