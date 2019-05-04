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
 * @property contentEncoded The `<content:enclosure>` element textContent of the Content namespace.
 * @property itunes The data from the iTunes namespace, or null if no data from this namespace was found.
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
    val contentEncoded: String?,
    val itunes: Episode.Itunes?
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
     * Model class for data from elements the iTunes namespace that are valid within `<item>` elements.
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
        val episodeType: String?
    )

}
