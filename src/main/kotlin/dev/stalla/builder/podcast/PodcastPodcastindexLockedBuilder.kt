package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Locked
import dev.stalla.util.whenNotNull

/** Builder for constructing [Locked] instances. */
public interface PodcastPodcastindexLockedBuilder : Builder<Locked> {

    /** Set the owner value. */
    public fun owner(owner: String): PodcastPodcastindexLockedBuilder

    /** Set the locked value. */
    public fun locked(locked: Boolean): PodcastPodcastindexLockedBuilder

    override fun applyFrom(prototype: Locked?): PodcastPodcastindexLockedBuilder {
        return whenNotNull(prototype) { podcastLocked ->
            owner(podcastLocked.owner)
            locked(podcastLocked.locked)
        }
    }
}
