package dev.stalla.builder.validating.episode

import com.google.common.net.MediaType
import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingEpisodePodcastindexChaptersBuilder : EpisodePodcastindexChaptersBuilder {

    private lateinit var urlValue: String
    private lateinit var typeValue: MediaType

    override fun url(url: String): EpisodePodcastindexChaptersBuilder = apply { this.urlValue = url }

    override fun type(type: MediaType): EpisodePodcastindexChaptersBuilder = apply { this.typeValue = type }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && ::typeValue.isInitialized

    override fun build(): Chapters? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Chapters(
            url = urlValue,
            type = typeValue
        )
    }
}
