package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Podcast.Podcast.Locked] instances. */
interface PodcastPodcastLockedBuilder : Builder<Podcast.Podcast.Locked> {

    /** Set the owner value. */
    fun owner(owner: String): PodcastPodcastLockedBuilder

    /** Set the locked value. */
    fun locked(locked: Boolean): PodcastPodcastLockedBuilder

    override fun from(model: Podcast.Podcast.Locked?): PodcastPodcastLockedBuilder = whenNotNull(model) { podcastLocked ->
        owner(podcastLocked.owner)
        locked(podcastLocked.locked)
    }
}
