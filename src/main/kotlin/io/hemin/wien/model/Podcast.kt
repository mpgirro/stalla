package io.hemin.wien.model

import java.util.*

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
    val episodes: List<Episode>
)
