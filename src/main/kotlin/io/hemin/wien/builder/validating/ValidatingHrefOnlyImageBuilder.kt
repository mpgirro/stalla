package io.hemin.wien.builder.validating

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.model.HrefOnlyImage

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
