package dev.stalla.model.itunes

import dev.stalla.builder.podcast.PodcastITunesBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastITunesBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.Person

/**
 * Model class for data from the iTunes namespace valid within an RSS `<channel>`.
 *
 * @property subtitle The `<itunes:subtitle>` field text content.
 * @property summary The `<itunes:summary>` field text content.
 * @property image The data from the `<itunes:image>` element as an [HrefOnlyImage].
 * @property keywords The `<itunes:keywords>` field text content.
 * @property author The `<itunes:author>` field text content.
 * @property categories The list of `<itunes:category>` element's field text contents.
 * @property explicit The logical value of the `<itunes:explicit>` field's text content.
 * @property block The logical value of the `<itunes:block>` field's text content.
 * @property complete The logical value of the `<itunes:complete>` field's text content.
 * @property type The `<itunes:type>` field text content.
 * @property owner The `<itunes:owner>` elements data as a [Person].
 * @property owner The `<itunes:title>` field text content.
 * @property owner The `<itunes:new-feed-url>` field text content.
 */
public data class PodcastItunes(
    override val subtitle: String? = null,
    override val summary: String? = null,
    override val image: HrefOnlyImage?,
    val keywords: String? = null,
    override val author: String? = null,
    val categories: List<ItunesStyleCategory>,
    val explicit: Boolean,
    override val block: Boolean,
    val complete: Boolean,
    val type: ShowType? = null,
    val owner: Person? = null,
    override val title: String? = null,
    val newFeedUrl: String? = null
) : ItunesBase {

    public companion object Factory : BuilderFactory<PodcastItunes, PodcastITunesBuilder> {

        /** Returns a builder implementation for building [PodcastItunes] model instances. */
        @JvmStatic
        override fun builder(): PodcastITunesBuilder = ValidatingPodcastITunesBuilder()
    }
}
