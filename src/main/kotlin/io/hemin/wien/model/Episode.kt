package io.hemin.wien.model

import java.time.ZonedDateTime

/**
 * Model class for all the properties extracted by parser implementations from RSS `<item>` elements.
 *
 * @property title The RSS `<title>` field value.
 * @property link The RSS `<link>` field value.
 * @property description The RSS `<description>` field value.
 * @property author The RSS `<author>` field value
 * @property categories List of RSS `<category>` field value.
 * @property comments The RSS `<comments>` field value.
 * @property enclosure The RSS `<enclosure>` element attributes wrapped in a [Episode.Enclosure] instance.
 * @property guid The RSS `<guid>` field value.
 * @property pubDate The RSS `<pubDate>` field value.
 * @property source The RSS `<source>` field value.
 * @property contentEncoded The `<content:enclosure>` element value of the Content namespace.
 */
data class Episode(
    val title: String?,
    val link: String?,
    val description: String?,
    val author: String?, // TODO turn to Person?
    val categories: List<String>, // TODO make Category class? can look like: <category domain="http://www.fool.com/cusips">MSFT</category>
    val comments: String?,
    val enclosure: Episode.Enclosure?,
    val guid: String?,
    val pubDate: ZonedDateTime?,
    val source: String?,
    val contentEncoded: String?
) {

    /**
     * @property url The `url` attribute value of the RSS `<enclosure>` element.
     * @property length The `length` attribute value of the RSS `<enclosure>` element.
     * @property type The `type` attribute value of the RSS `<enclosure>` element.
     */
    data class Enclosure(
        val url: String?,
        val length: Long?,
        val type: String?
    )

}
