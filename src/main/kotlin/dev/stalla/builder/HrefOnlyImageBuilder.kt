package dev.stalla.builder

import dev.stalla.model.HrefOnlyImage
import dev.stalla.util.whenNotNull

/** Builder for constructing [HrefOnlyImage] instances. */
public interface HrefOnlyImageBuilder : Builder<HrefOnlyImage> {

    /** Set the href value. */
    public fun href(href: String): HrefOnlyImageBuilder

    override fun applyFrom(prototype: HrefOnlyImage?): HrefOnlyImageBuilder =
        whenNotNull(prototype) { image -> href(image.href) }
}
