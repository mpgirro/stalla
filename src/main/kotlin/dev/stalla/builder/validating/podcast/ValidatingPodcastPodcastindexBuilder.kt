package dev.stalla.builder.validating.podcast

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcastindex
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingPodcastPodcastindexBuilder : PodcastPodcastindexBuilder {

    private var lockedBuilder: PodcastPodcastindexLockedBuilder? = null
    private var locationBuilder: PodcastindexLocationBuilder? = null
    private val fundingBuilders: MutableList<PodcastPodcastindexFundingBuilder> = mutableListOf()
    private val personBuilders: MutableList<PodcastindexPersonBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder =
        apply { this.lockedBuilder = lockedBuilder }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder =
        apply { fundingBuilders.add(fundingBuilder) }

    override fun addPersonBuilder(personBuilder: PodcastindexPersonBuilder): PodcastPodcastindexBuilder =
        apply { personBuilders.add(personBuilder) }

    override fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): PodcastPodcastindexBuilder =
        apply { this.locationBuilder = locationBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = lockedBuilder?.hasEnoughDataToBuild == true ||
            locationBuilder?.hasEnoughDataToBuild == true ||
            fundingBuilders.any { it.hasEnoughDataToBuild } ||
            personBuilders.any { it.hasEnoughDataToBuild }

    override fun build(): PodcastPodcastindex? {
        if (!hasEnoughDataToBuild) return null

        return PodcastPodcastindex(
            locked = lockedBuilder?.build(),
            funding = fundingBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            persons = personBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            location = locationBuilder?.build()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastPodcastindexBuilder) return false

        if (lockedBuilder != other.lockedBuilder) return false
        if (locationBuilder != other.locationBuilder) return false
        if (fundingBuilders != other.fundingBuilders) return false
        if (personBuilders != other.personBuilders) return false

        return true
    }

    override fun hashCode(): Int {
        var result = lockedBuilder?.hashCode() ?: 0
        result = 31 * result + (locationBuilder?.hashCode() ?: 0)
        result = 31 * result + fundingBuilders.hashCode()
        result = 31 * result + personBuilders.hashCode()
        return result
    }

    override fun toString() =
        "ValidatingPodcastPodcastindexBuilder(lockedBuilder=$lockedBuilder, locationBuilder=$locationBuilder, fundingBuilders=$fundingBuilders, " +
            "personBuilders=$personBuilders)"
}
