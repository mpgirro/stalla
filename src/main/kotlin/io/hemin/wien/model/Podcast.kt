package io.hemin.wien.model

import java.lang.UnsupportedOperationException
import java.time.ZonedDateTime

/**
 * Model class for all the properties extracted by parser implementations from RSS `<channel>` elements.
 *
 * @property title The RSS `<title>` field value.
 * @property link The RSS `<link>` field value.
 * @property description The RSS `<description>` field value.
 * @property pubDate The RSS `<pubDate>` field value.
 * @property lastBuildDate The RSS `<lastBuildDate>` field value.
 * @property language The RSS `<language>` field value.
 * @property generator The RSS `<generator>` field value.
 * @property copyright The RSS `<copyright>` field value.
 * @property docs The RSS `<docs>` field value.
 * @property managingEditor The RSS `<managingEditor>` field value.
 * @property webMaster The RSS `<webMaster>` field value.
 * @property episodes List of [Episode] instances extracted from the `<item>` entries of the RSS feed.
 */
class Podcast(
    val title: String?,
    val link: String?,
    val description: String?,
    val pubDate: ZonedDateTime?,
    val lastBuildDate: ZonedDateTime?,
    val language: String?,
    val generator: String?,
    val copyright: String?,
    val docs: String?,
    val managingEditor: String?,
    val webMaster: String?,
    //val image: Image?, // TODO
    val episodes: List<Episode>
) {
    /** Return a string representation of the instance */
    override fun toString(): String {
        return "Podcast(title=$title, link=$link, description=$description, pubDate=$pubDate, lastBuildDate=$lastBuildDate, language=$language, generator=$generator, copyright=$copyright, docs=$docs, managingEditor=$managingEditor, webMaster=$webMaster, episodes=$episodes)"
    }
}
