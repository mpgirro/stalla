package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast

internal interface PodcastPodcastBuilder : Builder<Podcast.Podcast> {

    /**
     * The builder for the Podcast namespace `<locked>` status.
     */
    fun lockedBuilder(lockedBuilder: PodcastPodcastLockedBuilder): PodcastPodcastBuilder

    /**
     * The builders for the Podcast namespace `<funding>` info.
     */
    fun addFundingBuilder(fundingBuilder: PodcastPodcastFundingBuilder): PodcastPodcastBuilder
}
