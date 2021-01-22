package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastPodcastFundingBuilder
import io.hemin.wien.model.Podcast

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

    override fun build(): Podcast.Podcast.Funding? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Podcast.Podcast.Funding(urlValue, messageValue)
    }
}
