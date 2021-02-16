package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Funding
import dev.stalla.util.whenNotNull

/** Builder for constructing [Funding] instances. */
public interface PodcastPodcastindexFundingBuilder : Builder<Funding> {

    /** Set the url value. */
    public fun url(url: String): PodcastPodcastindexFundingBuilder

    /** Set the message value */
    public fun message(message: String): PodcastPodcastindexFundingBuilder

    override fun applyFrom(prototype: Funding?): PodcastPodcastindexFundingBuilder = whenNotNull(prototype) { funding ->
        url(funding.url)
        message(funding.message)
    }
}
