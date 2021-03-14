package dev.stalla.model.podcastindex

import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastindexLockedBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.atom.Atom.Factory.builder
import dev.stalla.model.atom.AtomPerson.Factory.builder
import dev.stalla.model.podcastindex.Locked.Factory.builder

/**
 * The lock status of the podcast. Tells other podcast platforms whether they are allowed to
 * import this feed into their systems.
 *
 * Direct instantiation from Java is discouraged. Use the [builder] method
 * to obtain a builder instance for expressive construction instead.
 *
 * @property owner An email address that can be used to verify ownership when moving hosting platforms.
 * @property locked When `true`, the podcast cannot be transferred to a new hosting platform.
 *
 * @since 1.0.0
 */
public data class Locked(
    val owner: String,
    val locked: Boolean
) {

    /** Provides a builder for the [Locked] class. */
    public companion object Factory : BuilderFactory<Locked, PodcastPodcastindexLockedBuilder> {

        /** Returns a builder implementation for building [Locked] model instances. */
        @JvmStatic
        override fun builder(): PodcastPodcastindexLockedBuilder = ValidatingPodcastPodcastindexLockedBuilder()
    }
}
