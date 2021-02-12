package dev.stalla.builder.fake

import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.model.itunes.ItunesStyleCategory2

internal class FakeItunesStyleCategoryBuilder2 : FakeBuilder<ItunesStyleCategory2>(), ItunesStyleCategoryBuilder2 {

    var category: String? = null
    var subcategory: String? = null

    override fun category(category: String): ItunesStyleCategoryBuilder2 = apply { this.category = category }

    override fun subcategory(subcategory: String?): ItunesStyleCategoryBuilder2 = apply { this.subcategory = subcategory }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeItunesStyleCategoryBuilder2) return false

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
