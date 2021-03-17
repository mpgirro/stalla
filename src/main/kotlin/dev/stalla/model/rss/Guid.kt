package dev.stalla.model.rss

import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeGuidBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for `<guid>` elements within RSS `<item>` elements.
 *
 * Direct instantiation from Java is discouraged. Use the [builder][Guid.Factory.builder]
 * method to obtain an [EpisodeGuidBuilder] instance for expressive construction instead.
 *
 * @property guid The text content of the element.
 * @property isPermalink The boolean interpretation of the `isPermalink` attribute.
 *
 * @since 1.0.0
 */
public data class Guid(
    val guid: String,
    val isPermalink: Boolean? = null
) {

    /** Provides a builder for the [Guid] class. */
    public companion object Factory : BuilderFactory<Guid, EpisodeGuidBuilder> {

        /** Returns a builder implementation for building [Guid] model instances. */
        @JvmStatic
        override fun builder(): EpisodeGuidBuilder = ValidatingEpisodeGuidBuilder()
    }
}
