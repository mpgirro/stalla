package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastPodcastBuilder
import io.hemin.wien.builder.podcast.PodcastPodcastFundingBuilder
import io.hemin.wien.builder.podcast.PodcastPodcastLockedBuilder
import io.hemin.wien.model.Podcast

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

    override fun build(): Podcast.Podcast? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val locked = if (::lockedBuilderValue.isInitialized) lockedBuilderValue.build() else null
        return Podcast.Podcast(locked, fundingBuilders.mapNotNull { it.build() })
    }
}
