package io.hemin.wien.builder.validating

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.model.HrefOnlyImage

internal class ValidatingHrefOnlyImageBuilder : HrefOnlyImageBuilder {

    private lateinit var hrefValue: String

    override fun href(href: String): HrefOnlyImageBuilder = apply { this.hrefValue = href }

    override fun build(): HrefOnlyImage? {
        if (!::hrefValue.isInitialized) {
            return null
        }

        return HrefOnlyImage(hrefValue)
    }
}
