package dev.stalla.model.itunes

import dev.stalla.builder.podcast.PodcastItunesBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastItunesBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage

/**
 * Model class for data from the iTunes namespace valid within an RSS `<channel>`.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][PodcastItunes.Factory.builder]
 * method to obtain a [PodcastItunesBuilder] instance for expressive construction instead.
 *
 * @property subtitle The `<itunes:subtitle>` field text content.
 * @property summary The `<itunes:summary>` field text content.
 * @property image The data from the `<itunes:image>` element as an [HrefOnlyImage].
 * @property keywords The `<itunes:keywords>` field text content.
 * @property author The `<itunes:author>` field text content.
 * @property categories The list of `<itunes:category>` element's field text contents as [ItunesCategory].
 * @property explicit The logical value of the `<itunes:explicit>` field's text content.
 * @property block The logical value of the `<itunes:block>` field's text content.
 * @property complete The logical value of the `<itunes:complete>` field's text content.
 * @property type The value of the `<itunes:type>` field text content as a [ShowType].
 * @property owner The `<itunes:owner>` elements data as an [ItunesOwner].
 * @property title The `<itunes:title>` field text content.
 * @property newFeedUrl The `<itunes:new-feed-url>` field text content.
 *
 * @since 1.0.0
 */
public data class PodcastItunes(
    val subtitle: String? = null,
    val summary: String? = null,
    val image: HrefOnlyImage?,
    val keywords: String? = null,
    val author: String? = null,
    val categories: List<ItunesCategory>,
    val explicit: Boolean,
    val block: Boolean,
    val complete: Boolean,
    val type: ShowType? = null,
    val owner: ItunesOwner? = null,
    val title: String? = null,
    val newFeedUrl: String? = null
) {

    /** Provides a builder for the [PodcastItunes] class. */
    public companion object Factory : BuilderFactory<PodcastItunes, PodcastItunesBuilder> {

        /** Returns a builder implementation for building [PodcastItunes] model instances. */
        @JvmStatic
        override fun builder(): PodcastItunesBuilder = ValidatingPodcastItunesBuilder()
    }
}
