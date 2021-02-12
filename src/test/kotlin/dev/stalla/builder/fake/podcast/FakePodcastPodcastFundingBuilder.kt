package dev.stalla.builder.fake.podcast

import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.model.podcastns.Funding

internal class FakePodcastPodcastFundingBuilder : FakeBuilder<Funding>(), PodcastPodcastFundingBuilder {

    var url: String? = null
    var message: String? = null

    override fun url(url: String): PodcastPodcastFundingBuilder = apply {
        this.url = url
    }

    override fun message(message: String): PodcastPodcastFundingBuilder = apply {
        this.message = message
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastPodcastFundingBuilder) return false

        if (url != other.url) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url?.hashCode() ?: 0
        result = 31 * result + (message?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakePodcastPodcastFundingBuilder(url=$url, message=$message)"
}
