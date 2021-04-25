package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexSeasonBuilder
import dev.stalla.model.BuilderFactory

/**
 * TODO.
 *
 * @property number TODO.
 * @property name TODO.
 *
 * @see https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md#season
 * @since 1.1.0
 */
public data class PodcastindexSeason(
    val number: Double,
    val name: String?
) {
    /** Provides a builder for the [PodcastindexSeason] class. */
    public companion object Factory : BuilderFactory<PodcastindexSeason, EpisodePodcastindexSeasonBuilder> {

        /** Returns a builder implementation for building [PodcastindexSeason] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexSeasonBuilder = ValidatingEpisodePodcastindexSeasonBuilder()
    }
}
