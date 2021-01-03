package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastFeedpressBuilder
import io.hemin.wien.model.Podcast

internal class ValidatingPodcastFeedpressBuilder : PodcastFeedpressBuilder {

    private var newsletterId: String? = null
    private var locale: String? = null
    private var podcastId: String? = null
    private var cssFile: String? = null
    private var link: String? = null

    override fun newsletterId(newsletterId: String?): PodcastFeedpressBuilder = apply { this.newsletterId = newsletterId }

    override fun locale(locale: String?): PodcastFeedpressBuilder = apply { this.locale = locale }

    override fun podcastId(podcastId: String?): PodcastFeedpressBuilder = apply { this.podcastId = podcastId }

    override fun cssFile(cssFile: String?): PodcastFeedpressBuilder = apply { this.cssFile = cssFile }

    override fun link(link: String?): PodcastFeedpressBuilder = apply { this.link = link }

    override fun build(): Podcast.Feedpress? {
        if (allNull(newsletterId, locale, podcastId, cssFile, link)) {
            return null
        }

        return Podcast.Feedpress(
            newsletterId = newsletterId,
            locale = locale,
            podcastId = podcastId,
            cssFile = cssFile,
            link = link
        )
    }
}
