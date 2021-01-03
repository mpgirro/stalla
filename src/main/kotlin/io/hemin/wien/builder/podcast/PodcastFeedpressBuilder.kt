package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast

internal interface PodcastFeedpressBuilder : Builder<Podcast.Feedpress> {

    /** Set the newsletterId value. */
    fun newsletterId(newsletterId: String?): PodcastFeedpressBuilder

    /** Set the locale value. */
    fun locale(locale: String?): PodcastFeedpressBuilder

    /** Set the podcastId value. */
    fun podcastId(podcastId: String?): PodcastFeedpressBuilder

    /** Set the cssFile value. */
    fun cssFile(cssFile: String?): PodcastFeedpressBuilder

    /** Set the link value. */
    fun link(link: String?): PodcastFeedpressBuilder
}
