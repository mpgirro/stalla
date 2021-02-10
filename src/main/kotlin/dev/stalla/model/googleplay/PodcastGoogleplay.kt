package dev.stalla.model.googleplay

import dev.stalla.builder.podcast.PodcastGooglePlayBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastGooglePlayBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.itunes.ITunesStyleCategory

/**
 * Model class for data from the Google Play namespace valid within an RSS `<channel>`.
 *
 * @property author The `<googleplay:author>` field text content.
 * @property owner The `<googleplay:email>` field text content.
 * @property categories The list of `<googleplay:category>` element's field text contents.
 * @property description The `<googleplay:description>` field text content.
 * @property explicit The logical value of the `<googleplay:explicit>` field's text content.
 * @property block The logical value of the `<googleplay:block>` field's text content.
 * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
 */
public data class PodcastGoogleplay(
    val author: String? = null,
    val owner: String? = null,
    val categories: List<ITunesStyleCategory>,
    override val description: String? = null,
    override val explicit: Boolean? = null,
    override val block: Boolean,
    override val image: HrefOnlyImage? = null
) : GooglePlayBase {

    public companion object Factory : BuilderFactory<PodcastGoogleplay, PodcastGooglePlayBuilder> {

        /** Returns a builder implementation for building [PodcastGoogleplay] model instances. */
        @JvmStatic
        override fun builder(): PodcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
    }
}