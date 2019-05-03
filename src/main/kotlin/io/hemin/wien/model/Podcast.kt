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
