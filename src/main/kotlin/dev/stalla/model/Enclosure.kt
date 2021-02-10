package dev.stalla.model

import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeEnclosureBuilder

/**
 * Model class for `<enclosure>` elements within RSS `<item>` elements.
 *
 * @property url The `url` attribute textContent of the RSS `<enclosure>` element.
 * @property length The `length` attribute textContent of the RSS `<enclosure>` element. The media length in seconds.
 * @property type The `type` attribute textContent of the RSS `<enclosure>` element.
 */
public data class Enclosure(
    val url: String,
    val length: Long,
    val type: String
) {

    public companion object Factory : BuilderFactory<Enclosure, EpisodeEnclosureBuilder> {

        /** Returns a builder implementation for building [Enclosure] model instances. */
        @JvmStatic
        override fun builder(): EpisodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
    }
}
