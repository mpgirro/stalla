package io.hemin.wien.builder

import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.Feedpress] instances. */
class PodcastFeedpressBuilder : Builder<Podcast.Feedpress> {

    private var newsletterId: String? = null
    private var locale: String? = null
    private var podcastId: String? = null
    private var cssFile: String? = null

    /** Set the newsletterId value. */
    fun newsletterId(newsletterId: String?) = apply { this.newsletterId = newsletterId }

    /** Set the locale value. */
    fun locale(locale: String?) = apply { this.locale = locale }

    /** Set the podcastId value. */
    fun podcastId(podcastId: String?) = apply { this.podcastId = podcastId }

    /** Set the cssFile value. */
    fun cssFile(cssFile: String?) = apply { this.cssFile = cssFile }

    override fun build(): Podcast.Feedpress? {
        return if (anyNotNull(newsletterId, locale, podcastId, cssFile)) {
            Podcast.Feedpress(
                newsletterId = newsletterId,
                locale       = locale,
                podcastId    = podcastId,
                cssFile      = cssFile
            )
        } else {
            null
        }
    }

}
