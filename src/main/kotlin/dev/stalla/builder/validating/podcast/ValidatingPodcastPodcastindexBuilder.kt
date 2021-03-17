package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcastindex
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingPodcastPodcastindexBuilder : PodcastPodcastindexBuilder {

    private lateinit var lockedBuilderValue: PodcastPodcastindexLockedBuilder
    private val fundingBuilders: MutableList<PodcastPodcastindexFundingBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder =
        apply { this.lockedBuilderValue = lockedBuilder }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder =
        apply { fundingBuilders.add(fundingBuilder) }

    override val hasEnoughDataToBuild: Boolean
        get() = ::lockedBuilderValue.isInitialized &&
            lockedBuilderValue.hasEnoughDataToBuild ||
            fundingBuilders.any { it.hasEnoughDataToBuild }

    override fun build(): PodcastPodcastindex? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return PodcastPodcastindex(
            locked = if (::lockedBuilderValue.isInitialized) lockedBuilderValue.build() else null,
            funding = fundingBuilders.mapNotNull { it.build() }.asUnmodifiable()
        )
    }
}
