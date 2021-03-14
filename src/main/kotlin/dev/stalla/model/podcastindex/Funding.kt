package dev.stalla.model.podcastindex

import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastindexFundingBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.atom.Atom.Factory.builder
import dev.stalla.model.atom.AtomPerson.Factory.builder
import dev.stalla.model.podcastindex.Funding.Factory.builder

/**
 * The funding information for the podcast.
 *
 * Direct instantiation from Java is discouraged. Use the [builder] method
 * to obtain a builder instance for expressive construction instead.
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
