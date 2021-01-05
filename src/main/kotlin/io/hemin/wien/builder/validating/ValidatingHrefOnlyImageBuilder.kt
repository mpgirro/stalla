package io.hemin.wien.builder.validating

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.RssImageBuilder
import io.hemin.wien.model.Image

internal class ValidatingHrefOnlyImageBuilder : HrefOnlyImageBuilder {

    private lateinit var hrefValue: String

    override fun href(href: String): HrefOnlyImageBuilder = apply { this.hrefValue = href }

    override fun build(): Image.HrefOnlyImage? {
        if (!::hrefValue.isInitialized) {
            return null
        }

        return Image.HrefOnlyImage(hrefValue)
    }
}
