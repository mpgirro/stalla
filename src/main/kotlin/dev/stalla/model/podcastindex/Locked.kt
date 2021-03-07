package dev.stalla.model.podcastindex

import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastindexLockedBuilder
import dev.stalla.model.BuilderFactory

/**
 * The lock status of the podcast. Tells other podcast platforms whether they are allowed to
 * import this feed into their systems.
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
