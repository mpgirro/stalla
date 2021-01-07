package io.hemin.wien.model

import java.time.temporal.TemporalAccessor

/**
 * Model class for all the properties extracted by parser implementations from RSS `<channel>` elements.
 *
 * @property title The RSS `<title>` field textContent.
 * @property link The RSS `<link>` field textContent.
 * @property description The RSS `<description>` field textContent.
 * @property pubDate The RSS `<pubDate>` field textContent.
 * @property lastBuildDate The RSS `<lastBuildDate>` field textContent.
 * @property language The RSS `<language>` field textContent.
 * @property generator The RSS `<generator>` field textContent.
 * @property copyright The RSS `<copyright>` field textContent.
 * @property docs The RSS `<docs>` field textContent.
 * @property managingEditor The RSS `<managingEditor>` field textContent.
 * @property webMaster The RSS `<webMaster>` field textContent.
 * @property image The RSS `<image>` element wrapped in an [RssImage] instance.
 * @property episodes List of [Episode] instances extracted from the `<item>` entries of the RSS feed.
 * @property iTunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property fyyd The data from the Fyyd namespace, or null if no data from this namespace was found.
 * @property feedpress The data from the Feedpress namespace, or null if no data from this namespace was found.
 * @property googlePlay The data from the Google Play namespace, or null if no data from this namespace was found.
 */
data class Podcast(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: TemporalAccessor? = null,
    val lastBuildDate: TemporalAccessor? = null,
    val language: String,
    val generator: String? = null,
    val copyright: String? = null,
    val docs: String? = null,
    val managingEditor: String? = null,
    val webMaster: String? = null,
    val image: RssImage? = null,
    val episodes: List<Episode>,
    val iTunes: ITunes? = null,
    val atom: Atom? = null,
    val fyyd: Fyyd? = null,
    val feedpress: Feedpress? = null,
    val googlePlay: GooglePlay? = null
) {

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
    data class ITunes(
        override val subtitle: String? = null,
        override val summary: String? = null,
        override val image: HrefOnlyImage?,
        val keywords: String? = null,
        override val author: String? = null,
        val categories: List<String>, // TODO can be nested, Category() required?
        override val explicit: Boolean,
        override val block: Boolean? = null,
        val complete: Boolean? = null,
        val type: ShowType? = null,
        val owner: Person? = null,
        override val title: String? = null,
        val newFeedUrl: String? = null
    ) : ITunesBase {

        /**
         * Enum model for the defined values encountered within the
         * `<itunes:type>` element within a `<channel>` element.
         *
         * @property type The string representation of the Enum instance.
         */
        enum class ShowType(val type: String) {

            /** Type describing an episodic show. */
            EPISODIC("episodic"),

            /** Type describing a serial show. */
            SERIAL("serial");

            companion object {
                /**
                 * Factory method for the instance of the Enum matching the [type] parameter.
                 *
                 * @param type The string representation of the Enum instance.
                 * @return The Enum instance matching [type], or null if not matching instance exists.
                 */
                fun of(type: String?): ShowType? = type?.let {
                    values().find { t -> t.type == it.toLowerCase() }
                }
            }
        }
    }

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
    data class GooglePlay(
        val author: String? = null,
        val owner: String? = null,
        val categories: List<String>, // TODO can be nested, Category() required?
        override val description: String? = null,
        override val explicit: Boolean? = null,
        override val block: Boolean? = null,
        override val image: HrefOnlyImage? = null
    ) : GooglePlayBase

    /**
     * Model class for data from elements of the Atom namespace that are valid within `<channel>` elements.
     *
     * @property authors List of data from the `<atom:author>` elements as [Person] instances.
     * @property contributors List of data from the `<atom:contributor>` elements as [Person] instances.
     * @property links List of data from the `<atom:link>` elements as [Link] instances.
     */
    data class Atom(
        val authors: List<Person>,
        val contributors: List<Person>,
        val links: List<Link>
    )

    /**
     * Model class for data from elements of the Fyyd namespace that are valid within `<channel>` elements.
     *
     * @property verify The Podcast's verification token.
     */
    data class Fyyd(
        val verify: String
    )

    /**
     * Model class for data from elements of the Feedpress namespace that are valid within `<channel>` elements.
     *
     * @property newsletterId The ID of the FeedPress newsletter.
     * @property locale The feed template language.
     * @property podcastId The iTunes Podcast ID.
     * @property cssFile The feed's custom CSS file.
     * @property link An alternative link to podcast or RSS clients.
     */
    data class Feedpress(
        val newsletterId: String? = null,
        val locale: String? = null,
        val podcastId: String? = null,
        val cssFile: String? = null,
        val link: String? = null
    )
}
