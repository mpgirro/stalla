package io.hemin.wien.model

import java.util.*

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
 * @property image The RSS `<image>` element wrapped in an [Image] instance.
 * @property episodes List of [Episode] instances extracted from the `<item>` entries of the RSS feed.
 * @property itunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property fyyd The data from the Fyyd namespace, or null if no data from this namespace was found.
 * @property feedpress The data from the Feedpress namespace, or null if no data from this namespace was found.
 * @property googleplay The data from the Google Play namespace, or null if no data from this namespace was found.
 */
data class Podcast(
    val title: String?,
    val link: String?,
    val description: String?,
    val pubDate: Date?,
    val lastBuildDate: Date?,
    val language: String?,
    val generator: String?,
    val copyright: String?,
    val docs: String?,
    val managingEditor: String?,
    val webMaster: String?,
    val image: Image?,
    val episodes: List<Episode>,
    val itunes: Podcast.Itunes?,
    val atom: Podcast.Atom?,
    val fyyd: Podcast.Fyyd?,
    val feedpress: Podcast.Feedpress?,
    val googleplay: Podcast.Googleplay?
) {

    /**
     * Model class for data from the iTunes namespace valid within an RSS `<channel>`.
     *
     * @property subtitle The `<itunes:subtitle>` field text content.
     * @property summary The `<itunes:summary>` field text content.
     * @property image The data from the `<itunes:image>` element as an [Image].
     * @property keywords The `<itunes:keywords>` field text content.
     * @property author The `<itunes:author>` field text content.
     * @property categories The list of `<itunes:category>` element's field text contents.
     * @property explicit The logical value of the `<itunes:explicit>` field's text content.
     * @property block The logical value of the `<itunes:block>` field's text content.
     * @property complete The logical value of the `<itunes:complete>` field's text content.
     * @property type The `<itunes:type>` field text content.
     * @property owner The `<itunes:owner>` elements data as a [Person].
     */
    data class Itunes(
        val subtitle: String?,
        val summary: String?,
        val image: Image?,
        val keywords: String?,
        val author: String?, // TODO can this be a list? is this a Person() ?
        val categories: List<String?>, // TODO can be nested, Category() required?
        val explicit: Boolean?,
        val block: Boolean?,
        val complete: Boolean?,
        val type: ShowType?,
        val owner: Person?
    ) {
        /**
         * Enum model for the defined values encountered within the
         * `<itunes:type>` element within a `<channel>` element.
         *
         * @property type The string representation of the Enum instance.
         */
        enum class ShowType (val type: String) {

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
                    ShowType.values().find { t -> t.type == it.toLowerCase() }
                }
            }
        }
    }

    /**
     * Model class for data from the Google Play namespace valid within an RSS `<channel>`.
     *
     * @property author The `<googleplay:author>` field text content.
     * @property email The `<googleplay:email>` field text content.
     * @property categories The list of `<googleplay:category>` element's field text contents.
     * @property description The `<googleplay:description>` field text content.
     * @property explicit The logical value of the `<googleplay:explicit>` field's text content.
     * @property image The data from the `<googleplay:image>` element as an [Image].
     */
    data class Googleplay(
        val author: String?, // TODO can this be a list? is this a Person() ?
        val email: String?,  // TODO merge with author to a person?
        val categories: List<String?>, // TODO can be nested, Category() required?
        val description: String?,
        val explicit: Boolean?,
        val image: Image?
    )

    /**
     * Model class for data from elements of the Atom namespace that are valid within `<channel>` elements.
     *
     * @property authors List of data from the `<atom:author>` elements as [Person] instances.
     * @property contributors List of data from the `<atom:contributor>` elements as [Person] instances.
     * @property links List of data from the `<atom:link>` elements as [Link] instances.
     */
    data class Atom(
        val authors: List<Person>, // TODO is this found in <channel>'s?, or merely in <item>'s
        val contributors: List<Person>,
        val links: List<Link>
    )

    /**
     * Model class for data from elements of the Fyyd namespace that are valid within `<channel>` elements.
     *
     * @property verify The Podcast's verification token.
     */
    data class Fyyd(
        val verify: String?
    )

    /**
     * Model class for data from elements of the Feedpress namespace that are valid within `<channel>` elements.
     *
     * @property newsletterId The ID of the FeedPress newsletter.
     * @property locale The feed template language.
     * @property podcastId The iTunes Podcast ID.
     * @property cssFile The feed's custom CSS file.
     */
    data class Feedpress(
        val newsletterId: String?,
        val locale: String?,
        val podcastId: String?,
        val cssFile: String?
    )

}
