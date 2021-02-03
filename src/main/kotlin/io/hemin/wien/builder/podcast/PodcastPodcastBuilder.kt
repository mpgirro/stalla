package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Podcast.Podcast] instances. */
interface PodcastPodcastBuilder : Builder<Podcast.Podcast> {

    /**
     * The builder for the Podcast namespace `<locked>` status.
     */
    fun lockedBuilder(lockedBuilder: PodcastPodcastLockedBuilder): PodcastPodcastBuilder

    /**
     * The builders for the Podcast namespace `<funding>` info.
     */
    fun addFundingBuilder(fundingBuilder: PodcastPodcastFundingBuilder): PodcastPodcastBuilder

    fun addFundingBuilders(fundingBuilders: List<PodcastPodcastFundingBuilder>): PodcastPodcastBuilder = apply {
        fundingBuilders.forEach { fundingBuilder -> addFundingBuilder(fundingBuilder) }
    }

    override fun from(model: Podcast.Podcast?): PodcastPodcastBuilder = whenNotNull(model) { podcast ->
        lockedBuilder(Podcast.Podcast.Locked.builder().from(podcast.locked))
        addFundingBuilders(podcast.funding.map { funding -> Podcast.Podcast.Funding.builder().from(funding) })
    }
}
