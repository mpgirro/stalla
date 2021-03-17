package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexChaptersBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.MediaType

/**
 * The chapters information for the episode. See the
 * [reference docs](https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md#chapters).
 *
 * Direct instantiation in Java is discouraged. Use the [builder][Chapters.Factory.builder] method
 * to obtain an [EpisodePodcastindexChaptersBuilder] instance for expressive construction instead.
 *
 * @property url The URL for the chapters information.
 * @property type The MIME type of the chapters file. JSON (`application/json+chapters`) is the preferred format.
 *
 * @since 1.0.0
 */
public data class Chapters(
    val url: String,
    val type: MediaType
) {

    /** Provides a builder for the [Chapters] class. */
    public companion object Factory : BuilderFactory<Chapters, EpisodePodcastindexChaptersBuilder> {

        /** Returns a builder implementation for building [Chapters] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexChaptersBuilder = ValidatingEpisodePodcastindexChaptersBuilder()
    }
}
