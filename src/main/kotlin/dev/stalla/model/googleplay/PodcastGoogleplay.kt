package dev.stalla.model.googleplay

import dev.stalla.builder.podcast.PodcastGoogleplayBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastGoogleplayBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage

/**
 * Model class for data from the Google Play namespace valid within an RSS `<channel>`.
 *
 * Properties are as defined in the
 * [XML Schema](https://www.google.com/schemas/play-podcasts/1.0/play-podcasts.xsd)
 * for the Google Play Podcasts extension
 *
 * Direct instantiation in Java is discouraged. Use the [builder][PodcastGoogleplay.Factory.builder]
 * method to obtain a [PodcastGoogleplayBuilder] instance for expressive construction instead.
 *
 * @property author The `<googleplay:author>` element text content.
 * @property email The `<googleplay:email>` element text content.
 * @property categories The list of `<googleplay:category>` element text contents as [GoogleplayCategory].
 * @property description The `<googleplay:description>` element text content.
 * @property explicit The logical value of the `<googleplay:explicit>` element text content.
 * @property block The logical value of the `<googleplay:block>` element text content.
 * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
 * @property newFeedUrl The `<googleplay:newFeedUrl>` element text content.
 *
 * @since 1.0.0
 */
public data class PodcastGoogleplay(
    val author: String? = null,
    val email: String? = null,
    val categories: List<GoogleplayCategory>,
    val description: String? = null,
    val explicit: Boolean? = null,
    val block: Boolean,
    val image: HrefOnlyImage? = null,
    val newFeedUrl: String? = null
) {

    /** Provides a builder for the [PodcastGoogleplay] class. */
    public companion object Factory : BuilderFactory<PodcastGoogleplay, PodcastGoogleplayBuilder> {

        /** Returns a builder implementation for building [PodcastGoogleplay] model instances. */
        @JvmStatic
        override fun builder(): PodcastGoogleplayBuilder = ValidatingPodcastGoogleplayBuilder()
    }
}
