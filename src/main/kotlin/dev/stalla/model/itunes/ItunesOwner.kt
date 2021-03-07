package dev.stalla.model.itunes

import dev.stalla.builder.podcast.PodcastItunesOwnerBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastItunesOwnerBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for elements describing persons.
 *
 * @property name The name of the owner.
 * @property email The email of the owner.
 *
 * @since 1.0.0
 */
public data class ItunesOwner(
    val name: String,
    val email: String
) {

    /** Provides a builder for the [ItunesOwner] class. */
    public companion object Factory : BuilderFactory<ItunesOwner, PodcastItunesOwnerBuilder> {

        /** Returns a builder implementation for building [ItunesOwner] model instances. */
        @JvmStatic
        override fun builder(): PodcastItunesOwnerBuilder = ValidatingPodcastItunesOwnerBuilder()
    }
}
