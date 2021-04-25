package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.builder.GeoLocationBuilder
import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.Locked
import dev.stalla.model.podcastindex.PodcastPodcastindex
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [PodcastPodcastindex] instances.
 *
 * @since 1.0.0
 */
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
    public fun addAllFundingBuilders(
        fundingBuilders: List<PodcastPodcastindexFundingBuilder>
    ): PodcastPodcastindexBuilder = apply {
        fundingBuilders.forEach(::addFundingBuilder)
    }

    /**
     * Adds the [PodcastindexPersonBuilder] for the
     * `<podcast:person>` info to the list of person builders.
     */
    public fun addPersonBuilder(personBuilder: PodcastindexPersonBuilder): PodcastPodcastindexBuilder

    /**
     * Adds all of the [PodcastindexPersonBuilder] for the
     * `<podcast:person>` info to the list of person builders.
     */
    public fun addAllPersonBuilders(
        personBuilders: List<PodcastindexPersonBuilder>
    ): PodcastPodcastindexBuilder = apply {
        personBuilders.forEach(::addPersonBuilder)
    }

    /** Set the [PodcastindexLocationBuilder]. */
    public fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): PodcastPodcastindexBuilder

    override fun applyFrom(prototype: PodcastPodcastindex?): PodcastPodcastindexBuilder =
        whenNotNull(prototype) { podcast ->
            lockedBuilder(Locked.builder().applyFrom(podcast.locked))
            addAllFundingBuilders(podcast.funding.asBuilders())
            addAllPersonBuilders(podcast.persons.asBuilders())
            locationBuilder(PodcastindexLocation.builder().applyFrom(podcast.location))
        }
}
