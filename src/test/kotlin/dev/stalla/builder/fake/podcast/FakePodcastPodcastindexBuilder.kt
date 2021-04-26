package dev.stalla.builder.fake.podcast

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.podcastindex.PodcastPodcastindex

internal class FakePodcastPodcastindexBuilder : FakeBuilder<PodcastPodcastindex>(), PodcastPodcastindexBuilder {

    var lockedBuilderValue: PodcastPodcastindexLockedBuilder? = null
    var locationBuilderValue: PodcastindexLocationBuilder? = null
    val fundingBuilders: MutableList<PodcastPodcastindexFundingBuilder> = mutableListOf()
    val personBuilders: MutableList<PodcastindexPersonBuilder> = mutableListOf()

    override fun lockedBuilder(lockedBuilder: PodcastPodcastindexLockedBuilder): PodcastPodcastindexBuilder = apply {
        this.lockedBuilderValue = lockedBuilder
    }

    override fun addFundingBuilder(fundingBuilder: PodcastPodcastindexFundingBuilder): PodcastPodcastindexBuilder = apply {
        fundingBuilders.add(fundingBuilder)
    }

    override fun addPersonBuilder(personBuilder: PodcastindexPersonBuilder): PodcastPodcastindexBuilder = apply {
        personBuilders.add(personBuilder)
    }

    override fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): PodcastPodcastindexBuilder = apply {
        this.locationBuilderValue = locationBuilder
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakePodcastPodcastindexBuilder

        if (lockedBuilderValue != other.lockedBuilderValue) return false
        if (locationBuilderValue != other.locationBuilderValue) return false
        if (fundingBuilders != other.fundingBuilders) return false
        if (personBuilders != other.personBuilders) return false

        return true
    }

    override fun hashCode(): Int {
        var result = lockedBuilderValue?.hashCode() ?: 0
        result = 31 * result + (locationBuilderValue?.hashCode() ?: 0)
        result = 31 * result + fundingBuilders.hashCode()
        result = 31 * result + personBuilders.hashCode()
        return result
    }

    override fun toString(): String {
        return "FakePodcastPodcastindexBuilder(lockedBuilderValue=$lockedBuilderValue, locationBuilderValue=$locationBuilderValue, fundingBuilders=$fundingBuilders, personBuilders=$personBuilders)"
    }
}
