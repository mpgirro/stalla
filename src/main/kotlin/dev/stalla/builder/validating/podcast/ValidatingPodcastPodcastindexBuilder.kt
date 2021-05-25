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

    private var lockedBuilderValue: PodcastPodcastindexLockedBuilder? = null
    private var locationBuilder: PodcastindexLocationBuilder? = null
    private val fundingBuilders: MutableList<PodcastPodcastindexFundingBuilder> = mutableListOf()
    private val personBuilders: MutableList<PodcastindexPersonBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder =
        apply { this.lockedBuilderValue = lockedBuilder }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder =
        apply { fundingBuilders.add(fundingBuilder) }

    override fun addPersonBuilder(personBuilder: PodcastindexPersonBuilder): PodcastPodcastindexBuilder =
        apply { personBuilders.add(personBuilder) }

    override fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): PodcastPodcastindexBuilder =
        apply { this.locationBuilder = locationBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = lockedBuilderValue?.hasEnoughDataToBuild == true ||
            fundingBuilders.any { it.hasEnoughDataToBuild } ||
            personBuilders.any { it.hasEnoughDataToBuild } ||
            locationBuilder?.hasEnoughDataToBuild == true

    override fun build(): PodcastPodcastindex? {
        if (!hasEnoughDataToBuild) return null

        return PodcastPodcastindex(
            locked = lockedBuilderValue?.build(),
            funding = fundingBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            persons = personBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            location = locationBuilder?.build()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastPodcastindexBuilder) return false

        if (lockedBuilderValue != other.lockedBuilderValue) return false
        if (fundingBuilders != other.fundingBuilders) return false

        return true
    }

    override fun hashCode(): Int {
        var result = lockedBuilderValue.hashCode()
        result = 31 * result + fundingBuilders.hashCode()
        return result
    }

    override fun toString(): String =
        "ValidatingPodcastPodcastindexBuilder(lockedBuilder=$lockedBuilderValue, fundingBuilders=$fundingBuilders)"
}
