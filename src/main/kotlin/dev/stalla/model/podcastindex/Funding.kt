package dev.stalla.model.podcastindex

import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastindexFundingBuilder
import dev.stalla.model.BuilderFactory

/**
 * The funding information for the podcast.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][Funding.Factory.builder] method
 * to obtain a [PodcastPodcastindexFundingBuilder] instance for expressive construction instead.
 *
 * @property url The URL where listeners can find funding information for the podcast.
 * @property message The recommended CTA text to show users for the funding link.
 *
 * @since 1.0.0
 */
public data class Funding(
    val url: String,
    val message: String
) {

    /** Provides a builder for the [Funding] class. */
    public companion object Factory : BuilderFactory<Funding, PodcastPodcastindexFundingBuilder> {

        /** Returns a builder implementation for building [Funding] model instances. */
        @JvmStatic
        override fun builder(): PodcastPodcastindexFundingBuilder = ValidatingPodcastPodcastindexFundingBuilder()
    }
}
