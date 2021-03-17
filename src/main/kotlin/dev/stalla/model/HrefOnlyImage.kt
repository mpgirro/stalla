package dev.stalla.model

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder

/**
 * An `<image href="...">` tag. The `href` attribute is _mandatory_.
 *
 * Use the [builder][HrefOnlyImage.Factory.builder] method to obtain an
 * [HrefOnlyImageBuilder] instance for expressive construction in Java.
 *
 * @property href The value of the `href` attribute. Represents the image URL.
 *
 * @since 1.0.0
 */
public data class HrefOnlyImage(val href: String) {

    /** Provides a builder for the [HrefOnlyImage] class. */
    public companion object Factory : BuilderFactory<HrefOnlyImage, HrefOnlyImageBuilder> {

        /** Returns a builder implementation for building [HrefOnlyImage] model instances. */
        @JvmStatic
        override fun builder(): HrefOnlyImageBuilder = ValidatingHrefOnlyImageBuilder()
    }
}
