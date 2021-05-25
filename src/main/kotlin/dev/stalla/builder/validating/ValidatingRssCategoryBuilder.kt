package dev.stalla.builder.validating

import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.model.rss.RssCategory
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingRssCategoryBuilder : RssCategoryBuilder {

    private var category: String? = null

    private var domain: String? = null

    override fun category(category: String): RssCategoryBuilder = apply { this.category = category }

    override fun domain(domain: String?): RssCategoryBuilder = apply { this.domain = domain }

    override val hasEnoughDataToBuild: Boolean
        get() = category != null

    override fun build(): RssCategory? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return RssCategory(
            name = checkRequiredProperty(::category, "category name is missing"),
            domain = domain
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingRssCategoryBuilder) return false

        if (category != other.category) return false
        if (domain != other.domain) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category.hashCode()
        result = 31 * result + (domain?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "ValidatingRssCategoryBuilder(category='$category', domain=$domain)"
}
