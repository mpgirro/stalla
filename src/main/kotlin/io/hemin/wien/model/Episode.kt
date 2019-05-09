package io.hemin.wien.model

import java.util.*

/**
 * Model class for all the properties extracted by parser implementations from RSS `<item>` elements.
 *
 * @property title The RSS `<title>` field textContent.
 * @property link The RSS `<link>` field textContent.
 * @property description The RSS `<description>` field textContent.
 * @property author The RSS `<author>` field textContent
 * @property categories List of RSS `<category>` field textContent.
 * @property comments The RSS `<comments>` field textContent.
 * @property enclosure The RSS `<enclosure>` element attributes wrapped in an [Episode.Enclosure] instance.
 * @property guid The RSS `<guid>` element wrapped in an [Episode.Guid] instance.
 * @property pubDate The RSS `<pubDate>` field textContent.
 * @property source The RSS `<source>` field textContent.
 * @property content The data from the Content namespace, or null if no data from this namespace was found.
 * @property itunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property podlove The data from the Podlove standards namespaces, or null if no data from these namespaces were found.
 */
data class Episode(
    val title: String?,
    val link: String?,
    val description: String?,
    val author: String?, // TODO turn to Person?
    val categories: List<String>?, // TODO make Category class? can look like: <category domain="http://www.fool.com/cusips">MSFT</category>
    val comments: String?,
    val enclosure: Episode.Enclosure?,
    val guid: Episode.Guid?,
    val pubDate: Date?,
    val source: String?,
    val content: Episode.Content?,
    val itunes: Episode.Itunes?,
    val atom: Episode.Atom?,
    val podlove: Episode.Podlove?
) {

    /**
     * Model class for `<enclosure>` elements within RSS `<item>` elements.
     *
     * @property url The `url` attribute textContent of the RSS `<enclosure>` element.
     * @property length The `length` attribute textContent of the RSS `<enclosure>` element.
     * @property type The `type` attribute textContent of the RSS `<enclosure>` element.
     */
    data class Enclosure(
        val url: String?,
        val length: Long?,
        val type: String?
    )

    /**
     * Model class for `<guid>` elements within RSS `<item>` elements.
     *
     * @property textContent The text content of the element.
     * @property isPermalink The boolean interpretation of the `isPermalink` attribute.
     */
    data class Guid(
        val textContent: String?,
        val isPermalink: Boolean?
    )

    /**
     * Model class for data from elements of the Content namespace that are valid within `<item>` elements.
     *
     * @property encoded The text content of the `<content:encoded>` element.
     */
    data class Content(
        val encoded: String?
    )

    /**
     * Model class for data from elements of the iTunes namespace that are valid within `<item>` elements.
     *
     * @property title The `<itunes:title>` field text content.
     * @property duration The `<itunes:duration>` field text content.
     * @property image The data from the `<itunes:image>` element as an [Image].
     * @property explicit The logical value of the `<itunes:explicit>` field's text content.
     * @property block The logical value of the `<itunes:block>` field's text content.
     * @property season The numeric value of the `<itunes:season>` field's text content.
     * @property episode The numeric value of the `<itunes:episode>` field's text content.
     * @property episodeType The `<itunes:episodeType>` field text content.
     */
    data class Itunes(
        val title: String?,
        val duration: String?,
        val image: Image?,
        val explicit: Boolean?,
        val block: Boolean?,
        val season: Int?,
        val episode: Int?,
        val episodeType: EpisodeType?
    ) {
        /**
         * Enum model for the defined values encountered within the
         * `<itunes:episodeType>` element within a `<item>` element.
         *
         * @property type The string representation of the enum instance.
         */
        enum class EpisodeType (val type: String) {

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
                    EpisodeType.values().find { t -> t.type == it.toLowerCase() }
                }
            }
        }
    }

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
            val start: String?,
            val title: String?,
            val href: String?,
            val image: String?
        )

    }



}
