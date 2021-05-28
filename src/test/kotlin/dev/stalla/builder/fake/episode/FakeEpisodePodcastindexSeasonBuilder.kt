package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.podcastindex.PodcastindexSeason

internal class FakeEpisodePodcastindexSeasonBuilder : FakeBuilder<PodcastindexSeason>(), EpisodePodcastindexSeasonBuilder {

    var number: Int? = null
    var name: String? = null

    override fun number(number: Int): EpisodePodcastindexSeasonBuilder = apply { this.number = number }

    override fun name(name: String?): EpisodePodcastindexSeasonBuilder = apply { this.name = name }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakeEpisodePodcastindexSeasonBuilder

        if (number != other.number) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "FakeEpisodePodcastindexSeasonBuilder(number=$number, name=$name)"
}
