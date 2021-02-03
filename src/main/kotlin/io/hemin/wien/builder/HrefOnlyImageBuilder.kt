package io.hemin.wien.builder

import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [HrefOnlyImage] instances. */
interface HrefOnlyImageBuilder : Builder<HrefOnlyImage> {

    /** Set the href value. */
    fun href(href: String): HrefOnlyImageBuilder

    override fun from(model: HrefOnlyImage?): HrefOnlyImageBuilder = whenNotNull(model) { image ->
        href(image.href)
    }
}
