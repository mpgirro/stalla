package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.PodcastindexSeason
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [PodcastindexSeason] instances.
 *
 * @since 1.1.0
 */
public interface EpisodePodcastindexSeasonBuilder : Builder<PodcastindexSeason> {

    /** Set the number value. */
    public fun number(number: Int): EpisodePodcastindexSeasonBuilder

    /** Set the name value. */
    public fun name(name: String?): EpisodePodcastindexSeasonBuilder

    override fun applyFrom(prototype: PodcastindexSeason?): EpisodePodcastindexSeasonBuilder =
        whenNotNull(prototype) { season ->
            number(season.number)
            name(season.name)
        }
}
