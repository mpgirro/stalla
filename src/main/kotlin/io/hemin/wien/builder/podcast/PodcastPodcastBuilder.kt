package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.asBuilders
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Podcast.Podcast] instances. */
interface PodcastPodcastBuilder : Builder<Podcast.Podcast> {

    /** Set the [PodcastPodcastLockedBuilder]. */
    fun lockedBuilder(lockedBuilder: PodcastPodcastLockedBuilder): PodcastPodcastBuilder

    /**
     * Adds a [PodcastPodcastFundingBuilder] for the Podcast namespace `<funding>` info to the list of funding builders.
     */
    fun addFundingBuilder(fundingBuilder: PodcastPodcastFundingBuilder): PodcastPodcastBuilder

    /**
     * Adds multiple [PodcastPodcastFundingBuilder] for the Podcast namespace `<funding>` info to the list of funding builders.
     */
    fun addFundingBuilders(fundingBuilders: List<PodcastPodcastFundingBuilder>): PodcastPodcastBuilder = apply {
        fundingBuilders.forEach(::addFundingBuilder)
    }

    override fun from(model: Podcast.Podcast?): PodcastPodcastBuilder = whenNotNull(model) { podcast ->
        lockedBuilder(Podcast.Podcast.Locked.builder().from(podcast.locked))
        addFundingBuilders(podcast.funding.asBuilders())
    }
}
