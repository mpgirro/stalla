package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.model.podcastindex.Funding
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingPodcastPodcastindexFundingBuilder : PodcastPodcastindexFundingBuilder {

    private lateinit var urlValue: String
    private lateinit var messageValue: String

    override fun url(url: String): PodcastPodcastindexFundingBuilder = apply {
        this.urlValue = url
    }

    override fun message(message: String): PodcastPodcastindexFundingBuilder = apply {
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
