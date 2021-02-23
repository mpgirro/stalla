package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.model.StyledDuration
import dev.stalla.model.podcastindex.Soundbite
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingEpisodePodcastindexSoundbiteBuilder : EpisodePodcastindexSoundbiteBuilder {

    private lateinit var startTimeValue: StyledDuration.SecondsAndFraction
    private lateinit var durationValue: StyledDuration.SecondsAndFraction

    private var title: String? = null

    override fun startTime(startTime: StyledDuration.SecondsAndFraction): EpisodePodcastindexSoundbiteBuilder =
        apply { this.startTimeValue = startTime }

    override fun duration(duration: StyledDuration.SecondsAndFraction): EpisodePodcastindexSoundbiteBuilder =
        apply { this.durationValue = duration }

    override fun title(title: String?): EpisodePodcastindexSoundbiteBuilder = apply { this.title = title }

    override val hasEnoughDataToBuild: Boolean
        get() = ::startTimeValue.isInitialized && ::durationValue.isInitialized

    @Suppress("ReturnCount")
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
