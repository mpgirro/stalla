package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodcastindexEpisodeBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.podcastindex.PodcastindexEpisode

internal class FakeEpisodePodcastindexEpisodeBuilder : FakeBuilder<PodcastindexEpisode>(), EpisodePodcastindexEpisodeBuilder {

    var number: Double? = null
    var display: String? = null

    override fun number(number: Double): EpisodePodcastindexEpisodeBuilder = apply { this.number = number }

    override fun display(display: String?): EpisodePodcastindexEpisodeBuilder = apply { this.display = display }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakeEpisodePodcastindexEpisodeBuilder

        if (number != other.number) return false
        if (display != other.display) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number?.hashCode() ?: 0
        result = 31 * result + (display?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "FakeEpisodePodcastindexEpisodeBuilder(number=$number, display=$display)"
}
