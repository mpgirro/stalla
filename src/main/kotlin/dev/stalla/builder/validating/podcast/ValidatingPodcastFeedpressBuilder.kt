package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastFeedpressBuilder
import dev.stalla.model.feedpress.Feedpress
import dev.stalla.util.InternalAPI
import dev.stalla.util.anyNotNull
import java.util.Locale

@InternalAPI
internal class ValidatingPodcastFeedpressBuilder : PodcastFeedpressBuilder {

    private var newsletterId: String? = null
    private var locale: Locale? = null
    private var podcastId: String? = null
    private var cssFile: String? = null
    private var link: String? = null

    override fun newsletterId(newsletterId: String?): PodcastFeedpressBuilder =
        apply { this.newsletterId = newsletterId }

    override fun locale(locale: Locale?): PodcastFeedpressBuilder = apply { this.locale = locale }

    override fun podcastId(podcastId: String?): PodcastFeedpressBuilder = apply { this.podcastId = podcastId }

    override fun cssFile(cssFile: String?): PodcastFeedpressBuilder = apply { this.cssFile = cssFile }

    override fun link(link: String?): PodcastFeedpressBuilder = apply { this.link = link }

    override val hasEnoughDataToBuild: Boolean
        get() = anyNotNull(newsletterId, locale, podcastId, cssFile, link)

    override fun build(): Feedpress? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Feedpress(
            newsletterId = newsletterId,
            locale = locale,
            podcastId = podcastId,
            cssFile = cssFile,
            link = link
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastFeedpressBuilder) return false

        if (newsletterId != other.newsletterId) return false
        if (locale != other.locale) return false
        if (podcastId != other.podcastId) return false
        if (cssFile != other.cssFile) return false
        if (link != other.link) return false

        return true
    }

    override fun hashCode(): Int {
        var result = newsletterId?.hashCode() ?: 0
        result = 31 * result + (locale?.hashCode() ?: 0)
        result = 31 * result + (podcastId?.hashCode() ?: 0)
        result = 31 * result + (cssFile?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String =
        "ValidatingPodcastFeedpressBuilder(newsletterId=$newsletterId, locale=$locale, podcastId=$podcastId, cssFile=$cssFile, link=$link)"
}
