package io.hemin.wien.builder.fake

import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.model.RssCategory

internal class FakeRssCategoryBuilder : FakeBuilder<RssCategory>(), RssCategoryBuilder {

    var category: String? = null
    var domain: String? = null

    override fun category(category: String): RssCategoryBuilder = apply { this.category = category }

    override fun domain(domain: String?): RssCategoryBuilder = apply { this.domain = domain }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeRssCategoryBuilder) return false

        if (category != other.category) return false
        if (domain != other.domain) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category?.hashCode() ?: 0
        result = 31 * result + (domain?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakeRssCategoryBuilder(category=$category, domain=$domain)"
}
