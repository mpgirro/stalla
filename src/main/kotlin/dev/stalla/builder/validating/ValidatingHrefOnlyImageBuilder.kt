package dev.stalla.builder.validating

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingHrefOnlyImageBuilder : HrefOnlyImageBuilder {

    private lateinit var hrefValue: String

    override fun href(href: String): HrefOnlyImageBuilder = apply { this.hrefValue = href }

    override val hasEnoughDataToBuild: Boolean
        get() = ::hrefValue.isInitialized

    override fun build(): HrefOnlyImage? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return HrefOnlyImage(hrefValue)
    }
}
