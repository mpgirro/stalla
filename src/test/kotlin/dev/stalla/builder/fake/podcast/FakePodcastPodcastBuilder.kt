package dev.stalla.builder.fake.podcast

import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastPodcastBuilder
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcastindex

internal class FakePodcastPodcastBuilder : FakeBuilder<PodcastPodcastindex>(), PodcastPodcastBuilder {

    var lockedBuilderValue: PodcastPodcastLockedBuilder? = null
    val fundingBuilders: MutableList<PodcastPodcastFundingBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastLockedBuilder): PodcastPodcastBuilder = apply {
        this.lockedBuilderValue = lockedBuilder
    }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastFundingBuilder): PodcastPodcastBuilder = apply {
        fundingBuilders.add(fundingBuilder)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastPodcastBuilder) return false

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
