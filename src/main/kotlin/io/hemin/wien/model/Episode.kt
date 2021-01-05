package io.hemin.wien.model

import io.hemin.wien.model.Episode.Podlove.SimpleChapter
import java.time.temporal.TemporalAccessor

/**
 * Model class for all the properties extracted by parser implementations from RSS `<item>` elements.
 *
 * @property title The RSS `<title>` field textContent.
 * @property link The RSS `<link>` field textContent.
 * @property description The RSS `<description>` field textContent.
 * @property author The RSS `<author>` field textContent
 * @property categories List of RSS `<category>` field contents.
 * @property comments The RSS `<comments>` field textContent.
 * @property enclosure The RSS `<enclosure>` element attributes wrapped in an [Episode.Enclosure] instance.
 * @property guid The RSS `<guid>` element wrapped in an [Episode.Guid] instance.
 * @property pubDate The RSS `<pubDate>` field textContent.
 * @property source The RSS `<source>` field textContent.
 * @property content The data from the Content namespace, or null if no data from this namespace was found.
 * @property iTunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property podlove The data from the Podlove standards namespaces, or null if no data from these namespaces were found.
 * @property googlePlay The data from the Google Play namespace, or null if no data from this namespace was found.
 * @property bitlove The data from the Bitlove namespace, or null if no data from this namespace was found.
 */
@Suppress("unused")
data class Episode(
    val title: String,
    val link: String? = null,
    val description: String? = null,
    val author: String? = null,
    val categories: List<Category.Rss>,
    val comments: String? = null,
    val enclosure: Enclosure,
    val guid: Guid? = null,
    val pubDate: TemporalAccessor? = null,
    val source: String? = null,
    val content: Content? = null,
    val iTunes: ITunes? = null,
    val atom: Atom? = null,
    val podlove: Podlove? = null,
    val googlePlay: GooglePlay? = null,
    val bitlove: Bitlove? = null
) {

    /**
     * Model class for `<enclosure>` elements within RSS `<item>` elements.
     *
     * @property url The `url` attribute textContent of the RSS `<enclosure>` element.
     * @property length The `length` attribute textContent of the RSS `<enclosure>` element. The media length in seconds.
     * @property type The `type` attribute textContent of the RSS `<enclosure>` element.
     */
    data class Enclosure(
        val url: String,
        val length: Long,
        val type: String
    )

    /**
     * Model class for `<guid>` elements within RSS `<item>` elements.
     *
     * @property textContent The text content of the element.
     * @property isPermalink The boolean interpretation of the `isPermalink` attribute.
     */
    data class Guid(
        val textContent: String,
        val isPermalink: Boolean? = null
    )

    /**
     * Model class for data from elements of the Content namespace that are valid within `<item>` elements.
     *
     * @property encoded The text content of the `<content:encoded>` element.
     */
    data class Content(
        val encoded: String
    )

    /**
     * Model class for data from elements of the iTunes namespace that are valid within `<item>` elements.
     *
     * @property title The `<itunes:title>` field text content.
     * @property duration The `<itunes:duration>` field text content.
     * @property image The data from the `<itunes:image>` element as an [HrefOnlyImage].
     * @property explicit The logical value of the `<itunes:explicit>` field's text content.
     * @property block The logical value of the `<itunes:block>` field's text content.
     * @property season The numeric value of the `<itunes:season>` field's text content.
     * @property episode The numeric value of the `<itunes:episode>` field's text content.
     * @property episodeType The `<itunes:episodeType>` field text content.
     */
    data class ITunes(
        override val title: String? = null,
        val duration: String? = null,
        override val image: HrefOnlyImage? = null,
        override val explicit: Boolean? = null,
        override val block: Boolean? = null,
        val season: Int? = null,
        val episode: Int? = null,
        val episodeType: EpisodeType? = null,
        override val author: String? = null,
        override val subtitle: String? = null,
        override val summary: String? = null
    ) : ITunesBase {

        /**
         * Enum model for the defined values encountered within the
         * `<itunes:episodeType>` element within a `<item>` element.
         *
         * @property type The string representation of the enum instance.
         */
        enum class EpisodeType(val type: String) {

            /** Type describing a bonus episode. */
            BONUS("bonus"),

            /** Type describing a full episode. */
            FULL("full"),

            /** Type describing a trailer episode. */
            TRAILER("trailer");

            companion object {
                /**
                 * Factory method for the instance of the Enum matching the [type] parameter.
                 *
                 * @param type The string representation of the Enum instance.
                 * @return The Enum instance matching [type], or null if not matching instance exists.
                 */
                fun of(type: String?): EpisodeType? = type?.let {
                    values().find { t -> t.type == it.toLowerCase() }
                }
            }
        }
    }

    /**
     * Model class for data from elements of the Google Play namespace that are valid within `<item>` elements.
     *
     * @property description The `<googleplay:description>` field text content.
     * @property explicit The logical value of the `<googleplay:explicit>` field's text content.
     * @property block The logical value of the `<googleplay:block>` field's text content.
     * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
     */
    data class GooglePlay(
        override val description: String? = null,
        override val explicit: Boolean? = null,
        override val block: Boolean? = null,
        override val image: HrefOnlyImage? = null
    ) : GooglePlayBase

    /**
     * Model class for data from elements of the Atom namespace that are valid within `<item>` elements.
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
     * Model class for data from elements of namespaces from the Podlove
     * standards family that are valid within `<item>` elements.
     *
     * @property simpleChapters List of data from the `<psc:chapter>` elements as [SimpleChapter] instances.
     */
    data class Podlove(
        val simpleChapters: List<SimpleChapter>
    ) {

        /**
         * Model class for data from `<psc:chapter>` elements of the Podlove
         * Simple Chapter namespace that are valid within `<item>` elements.
         *
         * @property start The value of the chapter's `start` attribute.
         * @property title The value of the chapter's `title` attribute.
         * @property href The value of the chapter's `href` attribute.
         * @property image The value of the chapter's `image` attribute.
         */
        data class SimpleChapter(
            val start: String,
            val title: String,
            val href: String? = null,
            val image: String? = null
        )
    }

    /**
     * Model class for data from elements of the Bitlove namespace that are valid within `<item>` elements.
     *
     * @property guid The GUID attribute for the RSS enclosure element.
     */
    data class Bitlove(
        val guid: String
    )
}
