package dev.stalla.model.feedpress

import dev.stalla.builder.podcast.PodcastFeedpressBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastFeedpressBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Feedpress namespace that are valid within `<channel>` elements.
 *
 * @property newsletterId The ID of the FeedPress newsletter.
 * @property locale The feed template language.
 * @property podcastId The iTunes Podcast ID.
 * @property cssFile The feed's custom CSS file.
 * @property link An alternative link to podcast or RSS clients.
 */
public data class Feedpress(
    val newsletterId: String? = null,
    val locale: String? = null,
    val podcastId: String? = null,
    val cssFile: String? = null,
    val link: String? = null
) {

    /** Provides a builder for the [Feedpress] class. */
    public companion object Factory : BuilderFactory<Feedpress, PodcastFeedpressBuilder> {

        /** Returns a builder implementation for building [Feedpress] model instances. */
        @JvmStatic
        override fun builder(): PodcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
    }
}
