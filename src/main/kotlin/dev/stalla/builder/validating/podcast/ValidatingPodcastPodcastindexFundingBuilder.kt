package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.podcastindex.Funding
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingPodcastPodcastindexFundingBuilder : PodcastPodcastindexFundingBuilder {

    private var url: String? = null
    private var message: String? = null

    override fun url(url: String): PodcastPodcastindexFundingBuilder = apply { this.url = url }

    override fun message(message: String): PodcastPodcastindexFundingBuilder = apply { this.message = message }

    override val hasEnoughDataToBuild: Boolean
        get() = url != null && message != null

    override fun build(): Funding? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Funding(
            url = checkRequiredProperty(::url),
            message = checkRequiredProperty(::message)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastPodcastindexFundingBuilder) return false

        if (url != other.url) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + message.hashCode()
        return result
    }

    override fun toString(): String = "ValidatingPodcastPodcastindexFundingBuilder(url='$url', message='$message')"
}
