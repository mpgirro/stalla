package dev.stalla.builder.fake.podcast

import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastFeedpressBuilder
import dev.stalla.model.feedpress.Feedpress
import java.util.Locale

internal class FakePodcastFeedpressBuilder : FakeBuilder<Feedpress>(), PodcastFeedpressBuilder {

    var newsletterIdValue: String? = null
    var localeValue: Locale? = null
    var podcastIdValue: String? = null
    var cssFileValue: String? = null
    var linkValue: String? = null

    override fun newsletterId(newsletterId: String?): PodcastFeedpressBuilder = apply { this.newsletterIdValue = newsletterId }

    override fun locale(locale: Locale?): PodcastFeedpressBuilder = apply { this.localeValue = locale }

    override fun podcastId(podcastId: String?): PodcastFeedpressBuilder = apply { this.podcastIdValue = podcastId }

    override fun cssFile(cssFile: String?): PodcastFeedpressBuilder = apply { this.cssFileValue = cssFile }

    override fun link(link: String?): PodcastFeedpressBuilder = apply { this.linkValue = link }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastFeedpressBuilder) return false

        if (newsletterIdValue != other.newsletterIdValue) return false
        if (localeValue != other.localeValue) return false
        if (podcastIdValue != other.podcastIdValue) return false
        if (cssFileValue != other.cssFileValue) return false
        if (linkValue != other.linkValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = newsletterIdValue?.hashCode() ?: 0
        result = 31 * result + (localeValue?.hashCode() ?: 0)
        result = 31 * result + (podcastIdValue?.hashCode() ?: 0)
        result = 31 * result + (cssFileValue?.hashCode() ?: 0)
        result = 31 * result + (linkValue?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakePodcastFeedpressBuilder(newsletterIdValue=$newsletterIdValue, localeValue=$localeValue, podcastIdValue=$podcastIdValue, " +
            "cssFileValue=$cssFileValue, linkValue=$linkValue)"
}
