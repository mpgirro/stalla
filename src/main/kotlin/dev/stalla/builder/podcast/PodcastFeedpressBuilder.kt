package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.feedpress.Feedpress
import dev.stalla.util.whenNotNull
import java.util.Locale

/** Builder for constructing [Feedpress] instances. */
public interface PodcastFeedpressBuilder : Builder<Feedpress> {

    /** Set the newsletterId value. */
    public fun newsletterId(newsletterId: String?): PodcastFeedpressBuilder

    /** Set the locale value. */
    public fun locale(locale: Locale?): PodcastFeedpressBuilder

    /** Set the podcastId value. */
    public fun podcastId(podcastId: String?): PodcastFeedpressBuilder

    /** Set the cssFile value. */
    public fun cssFile(cssFile: String?): PodcastFeedpressBuilder

    /** Set the link value. */
    public fun link(link: String?): PodcastFeedpressBuilder

    override fun applyFrom(prototype: Feedpress?): PodcastFeedpressBuilder =
        whenNotNull(prototype) { feedpress ->
            newsletterId(feedpress.newsletterId)
            locale(feedpress.locale)
            podcastId(feedpress.podcastId)
            cssFile(feedpress.cssFile)
            link(feedpress.link)
        }
}
