package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastBuilder
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcast

internal class ValidatingPodcastPodcastBuilder : PodcastPodcastBuilder {

    private lateinit var lockedBuilderValue: PodcastPodcastLockedBuilder
    private val fundingBuilders: MutableList<PodcastPodcastFundingBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastLockedBuilder): PodcastPodcastBuilder = apply {
        this.lockedBuilderValue = lockedBuilder
    }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastFundingBuilder): PodcastPodcastBuilder = apply {
        fundingBuilders.add(fundingBuilder)
    }

    override val hasEnoughDataToBuild: Boolean
        get() = (::lockedBuilderValue.isInitialized && lockedBuilderValue.hasEnoughDataToBuild) ||
            fundingBuilders.any { it.hasEnoughDataToBuild }

    override fun build(): PodcastPodcast? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val locked = if (::lockedBuilderValue.isInitialized) lockedBuilderValue.build() else null
        return PodcastPodcast(locked, fundingBuilders.mapNotNull { it.build() })
    }
}
