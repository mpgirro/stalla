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
 * @property author The `<googleplay:author>` field text content.
 * @property email The `<googleplay:email>` field text content.
 * @property categories The list of `<googleplay:category>` element's field text contents as [GoogleplayCategory].
 * @property description The `<googleplay:description>` field text content.
 * @property explicit The logical value of the `<googleplay:explicit>` field's text content.
 * @property block The logical value of the `<googleplay:block>` field's text content.
 * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
 * @property newFeedUrl The `<googleplay:newFeedUrl>` field text content.
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
