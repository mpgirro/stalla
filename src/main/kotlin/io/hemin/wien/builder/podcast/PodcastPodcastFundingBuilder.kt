package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Podcast.Podcast.Funding] instances. */
interface PodcastPodcastFundingBuilder : Builder<Podcast.Podcast.Funding> {

    /** Set the url value. */
    fun url(url: String): PodcastPodcastFundingBuilder

    /** Set the message value */
    fun message(message: String): PodcastPodcastFundingBuilder

    override fun from(model: Podcast.Podcast.Funding?): PodcastPodcastFundingBuilder = whenNotNull(model) { funding ->
        url(funding.url)
        message(funding.message)
    }
}
