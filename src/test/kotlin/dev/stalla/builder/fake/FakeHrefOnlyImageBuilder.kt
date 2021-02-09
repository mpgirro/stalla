package dev.stalla.builder.fake

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage

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
