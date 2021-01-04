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

    override fun newsletterId(newsletterId: String?): PodcastFeedpressBuilder = apply { this.newsletterIdValue = newsletterId }

    override fun locale(locale: String?): PodcastFeedpressBuilder = apply { this.localeValue = locale }

    override fun podcastId(podcastId: String?): PodcastFeedpressBuilder = apply { this.podcastIdValue = podcastId }

    override fun cssFile(cssFile: String?): PodcastFeedpressBuilder = apply { this.cssFileValue = cssFile }

    override fun link(link: String?): PodcastFeedpressBuilder = apply { this.linkValue = link }
}
