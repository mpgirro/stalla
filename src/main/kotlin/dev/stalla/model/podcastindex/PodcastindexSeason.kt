package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexSeasonBuilder
import dev.stalla.model.BuilderFactory

/**
 * The season information for the podcast episode.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][PodcastindexSeason.Factory.builder]
 * method to obtain a [PodcastindexSeason] instance for expressive construction instead.
 *
 * @property number The season's number.
 * @property name The "name" of the season.
 *
 * @see https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md#season
 * @since 1.1.0
 */
public data class PodcastindexSeason(
    val number: Int,
    val name: String?
) {
    /** Provides a builder for the [PodcastindexSeason] class. */
    public companion object Factory : BuilderFactory<PodcastindexSeason, EpisodePodcastindexSeasonBuilder> {

        /** Returns a builder implementation for building [PodcastindexSeason] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexSeasonBuilder = ValidatingEpisodePodcastindexSeasonBuilder()
    }
}
