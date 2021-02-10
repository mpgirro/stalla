package dev.stalla.model

import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeGuidBuilder

/**
 * Model class for `<guid>` elements within RSS `<item>` elements.
 *
 * @property guid The text content of the element.
 * @property isPermalink The boolean interpretation of the `isPermalink` attribute.
 */
public data class Guid(
    val guid: String,
    val isPermalink: Boolean? = null
) {

    public companion object Factory : BuilderFactory<Guid, EpisodeGuidBuilder> {

        /** Returns a builder implementation for building [Guid] model instances. */
        @JvmStatic
        override fun builder(): EpisodeGuidBuilder = ValidatingEpisodeGuidBuilder()
    }
}
