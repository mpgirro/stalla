package dev.stalla.model.rss

import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeEnclosureBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.MediaType
import dev.stalla.model.atom.Atom.Factory.builder
import dev.stalla.model.atom.AtomPerson.Factory.builder
import dev.stalla.model.rss.Enclosure.Factory.builder

/**
 * Model class for `<enclosure>` elements within RSS `<item>` elements.
 *
 * Direct instantiation from Java is discouraged. Use the [builder] method
 * to obtain a builder instance for expressive construction instead.
 *
 * @property url The `url` attribute textContent of the RSS `<enclosure>` element.
 * @property length The `length` attribute textContent of the RSS `<enclosure>` element. The media length in seconds.
 * @property type The `type` attribute textContent of the RSS `<enclosure>` element.
 *
 * @since 1.0.0
 */
public data class Enclosure(
    val url: String,
    val length: Long,
    val type: MediaType
) {

    /** Provides a builder for the [Enclosure] class. */
    public companion object Factory : BuilderFactory<Enclosure, EpisodeEnclosureBuilder> {

        /** Returns a builder implementation for building [Enclosure] model instances. */
        @JvmStatic
        override fun builder(): EpisodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
    }
}
