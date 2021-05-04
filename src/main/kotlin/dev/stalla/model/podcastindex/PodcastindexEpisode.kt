package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexEpisodeBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexEpisodeBuilder
import dev.stalla.model.BuilderFactory

/**
 * The episode information for the podcast episode.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][PodcastindexEpisode.Factory.builder]
 * method to obtain a [PodcastindexEpisode] instance for expressive construction instead.
 *
 * @property number The episode's number.
 * @property display An alternative display name of the episode.
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
