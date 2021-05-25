package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcastindex
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingPodcastPodcastindexBuilder : PodcastPodcastindexBuilder {

    private var lockedBuilderValue: PodcastPodcastindexLockedBuilder? = null
    private val fundingBuilders: MutableList<PodcastPodcastindexFundingBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder =
        apply { this.lockedBuilderValue = lockedBuilder }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder =
        apply { fundingBuilders.add(fundingBuilder) }

    override val hasEnoughDataToBuild: Boolean
        get() = lockedBuilderValue?.hasEnoughDataToBuild == true || fundingBuilders.any { it.hasEnoughDataToBuild }

    override fun build(): PodcastPodcastindex? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return PodcastPodcastindex(
            locked = lockedBuilderValue?.build(),
            funding = fundingBuilders.mapNotNull { it.build() }.asUnmodifiable()
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
