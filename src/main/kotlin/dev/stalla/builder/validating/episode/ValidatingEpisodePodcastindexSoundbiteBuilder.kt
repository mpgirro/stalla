package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.model.podcastindex.Soundbite
import java.time.Duration

internal class ValidatingEpisodePodcastindexSoundbiteBuilder : EpisodePodcastindexSoundbiteBuilder {

    private lateinit var startTimeValue: Duration
    private lateinit var durationValue: Duration

    private var title: String? = null

    override fun startTime(startTime: Duration): EpisodePodcastindexSoundbiteBuilder = apply { this.startTimeValue = startTime }

    override fun duration(duration: Duration): EpisodePodcastindexSoundbiteBuilder = apply { this.durationValue = duration }

    override fun title(title: String?): EpisodePodcastindexSoundbiteBuilder = apply { this.title = title }

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
