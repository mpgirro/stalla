package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexEpisodeBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexEpisodeBuilder
import dev.stalla.model.BuilderFactory

/**
 * TODO.
 *
 * @property number TODO.
 * @property display TODO.
 *
 * @see https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md#episode
 * @since 1.1.0
 */
public data class PodcastindexEpisode(
    val number: Double,
    val display: String?
) {

    /** Provides a builder for the [PodcastindexEpisode] class. */
    public companion object Factory : BuilderFactory<PodcastindexEpisode, EpisodePodcastindexEpisodeBuilder> {

        /** Returns a builder implementation for building [PodcastindexEpisode] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexEpisodeBuilder = ValidatingEpisodePodcastindexEpisodeBuilder()
    }
}
