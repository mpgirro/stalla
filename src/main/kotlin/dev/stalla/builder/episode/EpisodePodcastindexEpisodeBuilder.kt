package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.PodcastindexEpisode
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [PodcastindexEpisode] instances.
 *
 * @since 1.1.0
 */
public interface EpisodePodcastindexEpisodeBuilder : Builder<PodcastindexEpisode> {

    /** Set the number value. */
    public fun number(number: Double): EpisodePodcastindexEpisodeBuilder

    /** Set the display value. */
    public fun display(display: String?): EpisodePodcastindexEpisodeBuilder

    override fun applyFrom(prototype: PodcastindexEpisode?): EpisodePodcastindexEpisodeBuilder =
        whenNotNull(prototype) { episode ->
            number(episode.number)
            display(episode.display)
        }
}
