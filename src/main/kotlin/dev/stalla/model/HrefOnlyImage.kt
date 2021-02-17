package dev.stalla.model

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder

/**
 * An `<image href="...">` tag. The `href` attribute is _mandatory_.
 *
 * @property href The value of the `href` attribute. Represents the image URL.
 */
public data class HrefOnlyImage(val href: String) {

    public companion object Factory : BuilderFactory<HrefOnlyImage, HrefOnlyImageBuilder> {

        /** Returns a builder implementation for building [HrefOnlyImage] model instances. */
        @JvmStatic
        override fun builder(): HrefOnlyImageBuilder = ValidatingHrefOnlyImageBuilder()
    }
}
