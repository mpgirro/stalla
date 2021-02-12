package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.model.podcastns.Funding

internal class ValidatingPodcastPodcastFundingBuilder : PodcastPodcastFundingBuilder {

    private lateinit var urlValue: String
    private lateinit var messageValue: String

    override fun url(url: String): PodcastPodcastFundingBuilder = apply {
        this.urlValue = url
    }

    override fun message(message: String): PodcastPodcastFundingBuilder = apply {
        this.messageValue = message
    }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && ::messageValue.isInitialized

    override fun build(): Funding? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Funding(urlValue, messageValue)
    }
}
