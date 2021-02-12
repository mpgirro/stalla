package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.podcastns.Locked
import dev.stalla.model.podcastns.PodcastPodcast
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [PodcastPodcast] instances. */
public interface PodcastPodcastBuilder : Builder<PodcastPodcast> {

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

    override fun from(model: PodcastPodcast?): PodcastPodcastBuilder = whenNotNull(model) { podcast ->
        lockedBuilder(Locked.builder().from(podcast.locked))
        addFundingBuilders(podcast.funding.asBuilders())
    }
}
