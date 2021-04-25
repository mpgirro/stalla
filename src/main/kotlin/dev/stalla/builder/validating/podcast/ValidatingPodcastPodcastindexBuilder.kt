package dev.stalla.builder.validating.podcast

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcastindex
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingPodcastPodcastindexBuilder : PodcastPodcastindexBuilder {

    private lateinit var lockedBuilderValue: PodcastPodcastindexLockedBuilder
    private var locationBuilderValue: PodcastindexLocationBuilder? = null
    private val fundingBuilders: MutableList<PodcastPodcastindexFundingBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder =
        apply { this.lockedBuilderValue = lockedBuilder }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder =
        apply { fundingBuilders.add(fundingBuilder) }

    override fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): PodcastPodcastindexBuilder =
        apply { this.locationBuilderValue = locationBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = ::lockedBuilderValue.isInitialized &&
            lockedBuilderValue.hasEnoughDataToBuild ||
            fundingBuilders.any { it.hasEnoughDataToBuild } ||
            locationBuilderValue?.hasEnoughDataToBuild == true

    override fun build(): PodcastPodcastindex? {
        if (!hasEnoughDataToBuild) return null

        return PodcastPodcastindex(
            locked = if (::lockedBuilderValue.isInitialized) lockedBuilderValue.build() else null,
            funding = fundingBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            location = locationBuilderValue?.build()
        )
    }
}
