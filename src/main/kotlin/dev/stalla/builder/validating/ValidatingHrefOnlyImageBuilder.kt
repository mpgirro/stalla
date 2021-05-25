package dev.stalla.builder.validating

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingHrefOnlyImageBuilder : HrefOnlyImageBuilder {

    private var href: String? = null

    override fun href(href: String): HrefOnlyImageBuilder = apply { this.href = href }

    override val hasEnoughDataToBuild: Boolean
        get() = href != null

    override fun build(): HrefOnlyImage? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return HrefOnlyImage(href = checkRequiredProperty(::href))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingHrefOnlyImageBuilder) return false

        if (href != other.href) return false

        return true
    }

    override fun hashCode(): Int = href.hashCode()

    override fun toString(): String = "ValidatingHrefOnlyImageBuilder(href='$href')"
}
