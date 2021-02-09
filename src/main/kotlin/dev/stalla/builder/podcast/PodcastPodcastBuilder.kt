package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.Podcast
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [Podcast.Podcast] instances. */
public interface PodcastPodcastBuilder : Builder<Podcast.Podcast> {

    /** Set the [PodcastPodcastLockedBuilder]. */
    public fun lockedBuilder(lockedBuilder: PodcastPodcastLockedBuilder): PodcastPodcastBuilder

    /**
     * Adds a [PodcastPodcastFundingBuilder] for the Podcast namespace `<funding>` info to the list of funding builders.
     */
    public fun addFundingBuilder(fundingBuilder: PodcastPodcastFundingBuilder): PodcastPodcastBuilder

    /**
     * Adds multiple [PodcastPodcastFundingBuilder] for the Podcast namespace `<funding>` info to the list of funding builders.
     */
    public fun addFundingBuilders(fundingBuilders: List<PodcastPodcastFundingBuilder>): PodcastPodcastBuilder = apply {
        fundingBuilders.forEach(::addFundingBuilder)
    }

    override fun from(model: Podcast.Podcast?): PodcastPodcastBuilder = whenNotNull(model) { podcast ->
        lockedBuilder(Podcast.Podcast.Locked.builder().from(podcast.locked))
        addFundingBuilders(podcast.funding.asBuilders())
    }
}