package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastindexEpisodeBuilder
import dev.stalla.model.podcastindex.PodcastindexEpisode
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodePodcastindexEpisodeBuilder : EpisodePodcastindexEpisodeBuilder {

    private var number: Double? = null
    private var display: String? = null

    override fun number(number: Double): EpisodePodcastindexEpisodeBuilder = apply { this.number = number }

    override fun display(display: String?): EpisodePodcastindexEpisodeBuilder = apply { this.display = display }

    override val hasEnoughDataToBuild: Boolean
        get() = number != null

    override fun build(): PodcastindexEpisode? {
        if (!hasEnoughDataToBuild) return null

        return PodcastindexEpisode(
            number = number ?: return null,
            display = display
        )
    }
}
