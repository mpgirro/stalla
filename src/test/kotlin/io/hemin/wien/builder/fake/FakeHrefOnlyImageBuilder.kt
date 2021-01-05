package io.hemin.wien.builder.fake

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.model.HrefOnlyImage

internal class FakeHrefOnlyImageBuilder : FakeBuilder<HrefOnlyImage>(), HrefOnlyImageBuilder {

    var href: String? = null

    override fun href(href: String): HrefOnlyImageBuilder = apply { this.href = href }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeHrefOnlyImageBuilder) return false

        if (href != other.href) return false

        return true
    }

    override fun hashCode(): Int {
        return href?.hashCode() ?: 0
    }

    override fun toString() = "FakeHrefOnlyImageBuilder(href=$href)"
}
