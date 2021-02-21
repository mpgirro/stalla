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
     * Adds the [PodcastPodcastindexFundingBuilder] for the
     * `<podcast:funding>` info to the list of funding builders.
     */
    public fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder

    /**
     * Adds all of the [PodcastPodcastindexFundingBuilder] for the
     * `<podcast:funding>` info to the list of funding builders.
     */
    public fun addAllFundingBuilder(
        fundingBuilders: List<PodcastPodcastindexFundingBuilder>
    ): PodcastPodcastindexBuilder = apply {
        fundingBuilders.forEach(::addFundingBuilder)
    }

    override fun applyFrom(prototype: PodcastPodcastindex?): PodcastPodcastindexBuilder {
        return whenNotNull(prototype) { podcast ->
            lockedBuilder(Locked.builder().applyFrom(podcast.locked))
            addAllFundingBuilder(podcast.funding.asBuilders())
        }
    }
}
