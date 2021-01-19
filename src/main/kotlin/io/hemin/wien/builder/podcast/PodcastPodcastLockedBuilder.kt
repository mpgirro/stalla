package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast

internal interface PodcastPodcastLockedBuilder: Builder<Podcast.Podcast.Locked> {

    fun owner(owner: String): PodcastPodcastLockedBuilder

    fun locked(locked: Boolean): PodcastPodcastLockedBuilder
}
