package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastChaptersBuilder
import dev.stalla.model.podcastindex.Chapters

internal class ValidatingEpisodePodcastChaptersBuilder : EpisodePodcastChaptersBuilder {

    private lateinit var urlValue: String
    private lateinit var typeValue: String

    override fun url(url: String): EpisodePodcastChaptersBuilder = apply { this.urlValue = url }

    override fun type(type: String): EpisodePodcastChaptersBuilder = apply { this.typeValue = type }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && ::typeValue.isInitialized

    override fun build(): Chapters? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Chapters(urlValue, typeValue)
    }
}
