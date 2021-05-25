package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.MediaType
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodePodcastindexChaptersBuilder : EpisodePodcastindexChaptersBuilder {

    private var url: String? = null
    private var type: MediaType? = null

    override fun url(url: String): EpisodePodcastindexChaptersBuilder = apply { this.url = url }

    override fun type(type: MediaType): EpisodePodcastindexChaptersBuilder = apply { this.type = type }

    override val hasEnoughDataToBuild: Boolean
        get() = url != null && type != null

    override fun build(): Chapters? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Chapters(
            url = checkRequiredProperty(::url),
            type = checkRequiredProperty(::type)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodePodcastindexChaptersBuilder) return false

        if (url != other.url) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

    override fun toString(): String = "ValidatingEpisodePodcastindexChaptersBuilder(url='$url', type=$type)"
}
