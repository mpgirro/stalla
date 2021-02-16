package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.StyledDuration
import dev.stalla.model.podcastindex.Soundbite

internal class FakeEpisodePodcastindexSoundbiteBuilder : FakeBuilder<Soundbite>(), EpisodePodcastindexSoundbiteBuilder {

    var startTime: StyledDuration.SecondsAndFraction? = null
    var duration: StyledDuration.SecondsAndFraction? = null
    var title: String? = null

    override fun startTime(startTime: StyledDuration.SecondsAndFraction): EpisodePodcastindexSoundbiteBuilder = apply { this.startTime = startTime }

    override fun duration(duration: StyledDuration.SecondsAndFraction): EpisodePodcastindexSoundbiteBuilder = apply { this.duration = duration }

    override fun title(title: String?): EpisodePodcastindexSoundbiteBuilder = apply { this.title = title }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodePodcastindexSoundbiteBuilder) return false

        if (startTime != other.startTime) return false
        if (duration != other.duration) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startTime?.hashCode() ?: 0
        result = 31 * result + (duration?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakeEpisodePodcastSoundbiteBuilder(startTime=$startTime, duration=$duration, title=$title)"
}
