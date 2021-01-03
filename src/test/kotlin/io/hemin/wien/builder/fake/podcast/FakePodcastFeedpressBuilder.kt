package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastFeedpressBuilder
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastFeedpressBuilder : FakeBuilder<Podcast.Feedpress>(), PodcastFeedpressBuilder {

    var newsletterIdValue: String? = null
    var localeValue: String? = null
    var podcastIdValue: String? = null
    var cssFileValue: String? = null
    var linkValue: String? = null

    /** Set the newsletterId value. */
    override fun newsletterId(newsletterId: String?): PodcastFeedpressBuilder = apply { this.newsletterIdValue = newsletterId }

    /** Set the locale value. */
    override fun locale(locale: String?): PodcastFeedpressBuilder = apply { this.localeValue = locale }

    /** Set the podcastId value. */
    override fun podcastId(podcastId: String?): PodcastFeedpressBuilder = apply { this.podcastIdValue = podcastId }

    /** Set the cssFile value. */
    override fun cssFile(cssFile: String?): PodcastFeedpressBuilder = apply { this.cssFileValue = cssFile }

    /** Set the link value. */
    override fun link(link: String?): PodcastFeedpressBuilder = apply { this.linkValue = link }
}
