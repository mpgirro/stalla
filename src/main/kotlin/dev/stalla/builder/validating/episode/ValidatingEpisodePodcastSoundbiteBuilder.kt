package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.model.podcastns.Soundbite
import java.time.Duration

internal class ValidatingEpisodePodcastSoundbiteBuilder : EpisodePodcastSoundbiteBuilder {

    private lateinit var startTimeValue: Duration
    private lateinit var durationValue: Duration

    private var title: String? = null

    override fun startTime(startTime: Duration): EpisodePodcastSoundbiteBuilder = apply { this.startTimeValue = startTime }

    override fun duration(duration: Duration): EpisodePodcastSoundbiteBuilder = apply { this.durationValue = duration }

    override fun title(title: String?): EpisodePodcastSoundbiteBuilder = apply { this.title = title }

    override val hasEnoughDataToBuild: Boolean
        get() = ::startTimeValue.isInitialized && ::durationValue.isInitialized

    override fun build(): Soundbite? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        if (startTimeValue.isNegative || durationValue.isNegative || durationValue.isZero) {
            return null
        }

        return Soundbite(startTimeValue, durationValue, title)
    }
}
