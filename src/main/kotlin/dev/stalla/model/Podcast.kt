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
 * Direct instantiation in Java is discouraged. Use the [builder][Podcast.Factory.builder]
 * method to obtain a [PodcastBuilder] instance for expressive construction instead.
 *
 * @property title The RSS `<title>` element text content.
 * @property link The RSS `<link>` element text content.
 * @property description The RSS `<description>` element text content.
 * @property pubDate The RSS `<pubDate>` element text content.
 * @property lastBuildDate The RSS `<lastBuildDate>` element text content.
 * @property language The RSS `<language>` element text content.
 * @property generator The RSS `<generator>` element text content.
 * @property copyright The RSS `<copyright>` element text content.
 * @property docs The RSS `<docs>` field element content.
 * @property managingEditor The RSS `<managingEditor>` element text content.
 * @property webMaster The RSS `<webMaster>` element text content.
 * @property ttl The RSS `<ttl>` element text content.
 * @property image The RSS `<image>` element wrapped in an [RssImage] instance.
 * @property episodes List of [Episode] instances extracted from the `<item>` entries of the RSS feed.
 * @property itunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property fyyd The data from the Fyyd namespace, or null if no data from this namespace was found.
 * @property feedpress The data from the Feedpress namespace, or null if no data from this namespace was found.
 * @property googleplay The data from the Google Play namespace, or null if no data from this namespace was found.
 * @property categories The RSS feed categories, if any.
 * @property podcastindex The data from the Podcastindex namespace, or null if no data from this namespace was found.
 *
 * @since 1.0.0
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
    val podcastindex: PodcastPodcastindex? = null
) {

    /** Provides a builder for the [Podcast] class. */
    public companion object Factory : BuilderFactory<Podcast, PodcastBuilder> {

        /** Returns a builder implementation for building [Podcast] model instances. */
        @JvmStatic
        override fun builder(): PodcastBuilder = ValidatingPodcastBuilder()
    }
}
