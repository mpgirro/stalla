package io.hemin.wien.builder

import io.hemin.wien.model.HrefOnlyImage

interface HrefOnlyImageBuilder : Builder<HrefOnlyImage> {

    /** Set the href value. */
    fun href(href: String): HrefOnlyImageBuilder
}
