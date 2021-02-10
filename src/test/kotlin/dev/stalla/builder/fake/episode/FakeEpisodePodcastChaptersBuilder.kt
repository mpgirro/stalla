package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodcastChaptersBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.podcastns.Chapters

internal class FakeEpisodePodcastChaptersBuilder : FakeBuilder<Chapters>(), EpisodePodcastChaptersBuilder {

    var url: String? = null
    var type: String? = null

    override fun url(url: String): EpisodePodcastChaptersBuilder = apply { this.url = url }

    override fun type(type: String): EpisodePodcastChaptersBuilder = apply { this.type = type }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodePodcastChaptersBuilder) return false

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
