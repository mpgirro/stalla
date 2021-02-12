package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Funding
import dev.stalla.util.whenNotNull

/** Builder for constructing [Funding] instances. */
public interface PodcastPodcastFundingBuilder : Builder<Funding> {

    /** Set the url value. */
    public fun url(url: String): PodcastPodcastFundingBuilder

    /** Set the message value */
    public fun message(message: String): PodcastPodcastFundingBuilder

    override fun from(model: Funding?): PodcastPodcastFundingBuilder = whenNotNull(model) { funding ->
        url(funding.url)
        message(funding.message)
    }
}
