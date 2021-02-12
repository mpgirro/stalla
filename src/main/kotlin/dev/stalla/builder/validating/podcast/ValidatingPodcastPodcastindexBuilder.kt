package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcastindex

internal class ValidatingPodcastPodcastindexBuilder : PodcastPodcastindexBuilder {

    private lateinit var lockedBuilderValue: PodcastPodcastindexLockedBuilder
    private val fundingBuilders: MutableList<PodcastPodcastindexFundingBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder = apply {
        this.lockedBuilderValue = lockedBuilder
    }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder = apply {
        fundingBuilders.add(fundingBuilder)
    }

    override val hasEnoughDataToBuild: Boolean
        get() = (::lockedBuilderValue.isInitialized && lockedBuilderValue.hasEnoughDataToBuild) ||
            fundingBuilders.any { it.hasEnoughDataToBuild }

    override fun build(): PodcastPodcastindex? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val locked = if (::lockedBuilderValue.isInitialized) lockedBuilderValue.build() else null
        return PodcastPodcastindex(locked, fundingBuilders.mapNotNull { it.build() })
    }
}
