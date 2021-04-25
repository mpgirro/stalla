package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.model.podcastindex.PodcastindexSeason
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodePodcastindexSeasonBuilder : EpisodePodcastindexSeasonBuilder {

    private var number: Double? = null
    private var name: String? = null

    override fun number(number: Double): EpisodePodcastindexSeasonBuilder = apply { this.number = number }

    override fun name(name: String?): EpisodePodcastindexSeasonBuilder = apply { this.name = name }

    override val hasEnoughDataToBuild: Boolean
        get() = number != null

    override fun build(): PodcastindexSeason? {
        if (!hasEnoughDataToBuild) return null

        return PodcastindexSeason(
            number = number ?: return null,
            name = name
        )
    }
}
