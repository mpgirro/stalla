package dev.stalla.model.podcastindex

import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastindexLockedBuilder
import dev.stalla.model.BuilderFactory

/**
 * The lock status of the podcast. Tells other podcast platforms whether they are allowed to
 * import this feed into their systems.
 *
 * @param owner An email address that can be used to verify ownership when moving hosting platforms.
 * @param locked When `true`, the podcast cannot be transferred to a new hosting platform.
 */
public data class Locked(
    val owner: String,
    val locked: Boolean
) {

    public companion object Factory : BuilderFactory<Locked, PodcastPodcastindexLockedBuilder> {

        /** Returns a builder implementation for building [Locked] model instances. */
        @JvmStatic
        override fun builder(): PodcastPodcastindexLockedBuilder = ValidatingPodcastPodcastindexLockedBuilder()
    }
}
