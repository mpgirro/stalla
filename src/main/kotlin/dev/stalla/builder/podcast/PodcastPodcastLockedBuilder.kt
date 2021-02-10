package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.Podcast
import dev.stalla.model.podcastns.Locked
import dev.stalla.util.whenNotNull

/** Builder for constructing [Podcast.Podcast.Locked] instances. */
public interface PodcastPodcastLockedBuilder : Builder<Locked> {

    /** Set the owner value. */
    public fun owner(owner: String): PodcastPodcastLockedBuilder

    /** Set the locked value. */
    public fun locked(locked: Boolean): PodcastPodcastLockedBuilder

    override fun from(model: Locked?): PodcastPodcastLockedBuilder = whenNotNull(model) { podcastLocked ->
        owner(podcastLocked.owner)
        locked(podcastLocked.locked)
    }
}
