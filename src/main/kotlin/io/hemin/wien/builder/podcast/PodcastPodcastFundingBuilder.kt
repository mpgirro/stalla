package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast

/** Builder for constructing [Podcast.Podcast.Funding] instances. */
interface PodcastPodcastFundingBuilder : Builder<Podcast.Podcast.Funding> {

    fun url(url: String): PodcastPodcastFundingBuilder

    fun message(message: String): PodcastPodcastFundingBuilder
}
