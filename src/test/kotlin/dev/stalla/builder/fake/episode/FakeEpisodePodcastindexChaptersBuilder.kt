package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.MediaType
import dev.stalla.model.podcastindex.Chapters

internal class FakeEpisodePodcastindexChaptersBuilder : FakeBuilder<Chapters>(), EpisodePodcastindexChaptersBuilder {

    var url: String? = null
    var type: MediaType? = null

    override fun url(url: String): EpisodePodcastindexChaptersBuilder = apply { this.url = url }

    override fun type(type: MediaType): EpisodePodcastindexChaptersBuilder = apply { this.type = type }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodePodcastindexChaptersBuilder) return false

        if (url != other.url) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url?.hashCode() ?: 0
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakeEpisodePodcastChaptersBuilder(url=$url, type=$type)"
}
