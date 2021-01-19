package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodePodcastSoundbiteBuilder
import io.hemin.wien.model.Episode
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

    override fun build(): Episode.Podcast.Soundbite? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        if (startTimeValue.isNegative || durationValue.isNegative || durationValue.isZero) {
            return null
        }

        return Episode.Podcast.Soundbite(startTimeValue, durationValue, title)
    }
}
