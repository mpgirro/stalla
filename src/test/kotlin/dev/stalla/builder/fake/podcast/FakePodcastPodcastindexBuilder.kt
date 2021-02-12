package dev.stalla.builder.fake.podcast

import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcastindex

internal class FakePodcastPodcastindexBuilder : FakeBuilder<PodcastPodcastindex>(), PodcastPodcastindexBuilder {

    var lockedBuilderValue: PodcastPodcastindexLockedBuilder? = null
    val fundingBuilders: MutableList<PodcastPodcastindexFundingBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder = apply {
        this.lockedBuilderValue = lockedBuilder
    }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder = apply {
        fundingBuilders.add(fundingBuilder)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastPodcastindexBuilder) return false

        if (fundingBuilders != other.fundingBuilders) return false
        if (lockedBuilderValue != other.lockedBuilderValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fundingBuilders.hashCode()
        result = 31 * result + lockedBuilderValue.hashCode()
        return result
    }

    override fun toString() = "FakePodcastPodcastBuilder(fundingBuilders=$fundingBuilders, lockedBuilder=$lockedBuilderValue)"
}
