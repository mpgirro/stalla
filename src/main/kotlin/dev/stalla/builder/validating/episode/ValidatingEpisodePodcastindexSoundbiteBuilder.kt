package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.StyledDuration
import dev.stalla.model.podcastindex.Soundbite
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodePodcastindexSoundbiteBuilder : EpisodePodcastindexSoundbiteBuilder {

    private var startTime: StyledDuration.SecondsAndFraction? = null
    private var duration: StyledDuration.SecondsAndFraction? = null

    private var title: String? = null

    override fun startTime(startTime: StyledDuration.SecondsAndFraction): EpisodePodcastindexSoundbiteBuilder =
        apply { this.startTime = startTime }

    override fun duration(duration: StyledDuration.SecondsAndFraction): EpisodePodcastindexSoundbiteBuilder =
        apply { this.duration = duration }

    override fun title(title: String?): EpisodePodcastindexSoundbiteBuilder = apply { this.title = title }

    override val hasEnoughDataToBuild: Boolean
        get() = startTime != null && duration != null

    override fun build(): Soundbite? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val startTimeValid = checkRequiredProperty(::startTime, "start time is missing")
        val durationValid = checkRequiredProperty(::duration)

        if (startTimeValid.isNegative || durationValid.isNegative || durationValid.isZero) {
            return null
        }

        return Soundbite(
            startTime = startTimeValid,
            duration = durationValid,
            title = title
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodePodcastindexSoundbiteBuilder) return false

        if (startTime != other.startTime) return false
        if (duration != other.duration) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startTime.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String =
        "ValidatingEpisodePodcastindexSoundbiteBuilder(startTime=$startTime, duration=$duration, title=$title)"
}
