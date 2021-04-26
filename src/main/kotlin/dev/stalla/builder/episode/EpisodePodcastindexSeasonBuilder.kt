package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.PodcastindexSeason
import dev.stalla.util.whenNotNull

public interface EpisodePodcastindexSeasonBuilder : Builder<PodcastindexSeason> {

    public fun number(number: Int): EpisodePodcastindexSeasonBuilder

    public fun name(name: String?): EpisodePodcastindexSeasonBuilder

    override fun applyFrom(prototype: PodcastindexSeason?): EpisodePodcastindexSeasonBuilder =
        whenNotNull(prototype) { season ->
            number(season.number)
            name(season.name)
        }
}
