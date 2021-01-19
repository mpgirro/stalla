package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodePodcastChaptersBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

internal class FakeEpisodePodcastChaptersBuilder : FakeBuilder<Episode.Podcast.Chapters>(), EpisodePodcastChaptersBuilder {

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
