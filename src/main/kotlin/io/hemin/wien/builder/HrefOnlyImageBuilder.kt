package io.hemin.wien.builder

import io.hemin.wien.model.Image

internal interface HrefOnlyImageBuilder : Builder<Image.HrefOnlyImage> {

    /** Set the href value. */
    fun href(href: String): HrefOnlyImageBuilder
}
