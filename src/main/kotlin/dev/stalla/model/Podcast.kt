package dev.stalla.model

import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastBuilder
import dev.stalla.model.atom.Atom
import dev.stalla.model.feedpress.Feedpress
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.podcastindex.PodcastPodcastindex
import dev.stalla.model.rss.RssCategory
import dev.stalla.model.rss.RssImage
import java.time.temporal.TemporalAccessor
import java.util.Locale

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
 * @property ttl The RSS `<ttl>` field textContent.
 * @property image The RSS `<image>` element wrapped in an [RssImage] instance.
 * @property episodes List of [Episode] instances extracted from the `<item>` entries of the RSS feed.
 * @property itunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property fyyd The data from the Fyyd namespace, or null if no data from this namespace was found.
 * @property feedpress The data from the Feedpress namespace, or null if no data from this namespace was found.
 * @property googleplay The data from the Google Play namespace, or null if no data from this namespace was found.
 * @property categories The RSS feed categories, if any.
 * @property podcast The data from the Podcastindex namespace, or null if no data from this namespace was found.
 */
public data class Podcast(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: TemporalAccessor? = null,
    val lastBuildDate: TemporalAccessor? = null,
    val language: Locale,
    val generator: String? = null,
    val copyright: String? = null,
    val docs: String? = null,
    val managingEditor: String? = null,
    val webMaster: String? = null,
    val ttl: Int? = null,
    val image: RssImage? = null,
    val episodes: List<Episode>,
    val itunes: PodcastItunes? = null,
    val atom: Atom? = null,
    val fyyd: Fyyd? = null,
    val feedpress: Feedpress? = null,
    val googleplay: PodcastGoogleplay? = null,
    val categories: List<RssCategory> = emptyList(),
    val podcast: PodcastPodcastindex? = null
) {

    /** Provides a builder for the [Podcast] class. */
    public companion object Factory : BuilderFactory<Podcast, PodcastBuilder> {

        /** Returns a builder implementation for building [Podcast] model instances. */
        @JvmStatic
        override fun builder(): PodcastBuilder = ValidatingPodcastBuilder()
    }
}
