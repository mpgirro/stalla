package dev.stalla.model.fyyd

import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastFyydBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Fyyd namespace that are valid within `<channel>` elements.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][Fyyd.Factory.builder]
 * method to obtain a [PodcastFyydBuilder] instance for expressive construction instead.
 *
 * @property verify The Podcast's verification token.
 *
 * @since 1.0.0
 */
public data class Fyyd(
    val verify: String
) {

    /** Provides a builder for the [Fyyd] class. */
    public companion object Factory : BuilderFactory<Fyyd, PodcastFyydBuilder> {

        /** Returns a builder implementation for building [Fyyd] model instances. */
        @JvmStatic
        override fun builder(): PodcastFyydBuilder = ValidatingPodcastFyydBuilder()
    }
}
