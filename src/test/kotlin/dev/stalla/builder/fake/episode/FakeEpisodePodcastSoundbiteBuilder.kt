package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.podcastns.Soundbite
import java.time.Duration

internal class FakeEpisodePodcastSoundbiteBuilder : FakeBuilder<Soundbite>(), EpisodePodcastSoundbiteBuilder {

    var startTime: Duration? = null
    var duration: Duration? = null
    var title: String? = null

    override fun startTime(startTime: Duration): EpisodePodcastSoundbiteBuilder = apply { this.startTime = startTime }

    override fun duration(duration: Duration): EpisodePodcastSoundbiteBuilder = apply { this.duration = duration }

    override fun title(title: String?): EpisodePodcastSoundbiteBuilder = apply { this.title = title }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodePodcastSoundbiteBuilder) return false

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
