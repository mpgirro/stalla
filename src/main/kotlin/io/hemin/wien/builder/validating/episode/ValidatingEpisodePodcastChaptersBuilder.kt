package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodePodcastChaptersBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodePodcastChaptersBuilder : EpisodePodcastChaptersBuilder {

    private lateinit var urlValue: String
    private lateinit var typeValue: String

    override fun url(url: String): EpisodePodcastChaptersBuilder = apply { this.urlValue = url }

    override fun type(type: String): EpisodePodcastChaptersBuilder = apply { this.typeValue = type }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && ::typeValue.isInitialized

    override fun build(): Episode.Podcast.Chapters? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode.Podcast.Chapters(urlValue, typeValue)
    }
}
