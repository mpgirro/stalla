package dev.stalla.model.fyyd

import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastFyydBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Fyyd namespace that are valid within `<channel>` elements.
 *
 * @property verify The Podcast's verification token.
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
