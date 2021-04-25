package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.PodcastindexEpisode
import dev.stalla.util.whenNotNull

public interface EpisodePodcastindexEpisodeBuilder : Builder<PodcastindexEpisode> {

    public fun number(number: Double): EpisodePodcastindexEpisodeBuilder

    public fun display(display: String?): EpisodePodcastindexEpisodeBuilder

    override fun applyFrom(prototype: PodcastindexEpisode?): EpisodePodcastindexEpisodeBuilder =
        whenNotNull(prototype) { episode ->
            number(episode.number)
            display(episode.display)
        }
}
