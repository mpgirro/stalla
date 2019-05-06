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
    val atom: Podcast.Atom?
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
        val type: String?,
        val owner: Person?
    )


    data class Atom(
        val authors: List<Person>, // TODO is this found in <channel>'s?, or merely in <item>'s
        val contributors: List<Person>,
        val links: List<Link>
    )

}
