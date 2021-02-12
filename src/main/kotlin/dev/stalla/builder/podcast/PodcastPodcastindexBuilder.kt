package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Locked
import dev.stalla.model.podcastindex.PodcastPodcastindex
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [PodcastPodcastindex] instances. */
public interface PodcastPodcastindexBuilder : Builder<PodcastPodcastindex> {

    /** Set the [PodcastPodcastindexLockedBuilder]. */
    public fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder

    /**
     * Adds a [PodcastPodcastindexFundingBuilder] for the Podcast namespace `<funding>` info to the list of funding builders.
     */
    public fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder

    /**
     * Adds multiple [PodcastPodcastindexFundingBuilder] for the Podcast namespace `<funding>` info to the list of funding builders.
     */
    public fun addFundingBuilders(fundingBuilders: List<PodcastPodcastindexFundingBuilder>): PodcastPodcastindexBuilder = apply {
        fundingBuilders.forEach(::addFundingBuilder)
    }

    override fun from(model: PodcastPodcastindex?): PodcastPodcastindexBuilder = whenNotNull(model) { podcast ->
        lockedBuilder(Locked.builder().from(podcast.locked))
        addFundingBuilders(podcast.funding.asBuilders())
    }
}
