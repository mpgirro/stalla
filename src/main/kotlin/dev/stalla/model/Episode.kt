package dev.stalla.model

import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeBuilder
import dev.stalla.model.atom.Atom
import dev.stalla.model.bitlove.Bitlove
import dev.stalla.model.content.Content
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.model.podlove.EpisodePodlove
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.Guid
import dev.stalla.model.rss.RssCategory
import java.time.temporal.TemporalAccessor

/**
 * Model class for all the properties extracted by parser implementations from RSS `<item>` elements.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][Episode.Factory.builder]
 * method to obtain an [EpisodeBuilder] instance for expressive construction instead.
 *
 * @property title The RSS `<title>` element text content.
 * @property link The RSS `<link>` element text content.
 * @property description The RSS `<description>` element text content.
 * @property author The RSS `<author>` element text content
 * @property categories List of RSS `<category>` element text contents.
 * @property comments The RSS `<comments>` element text content.
 * @property enclosure The RSS `<enclosure>` element attributes wrapped in an [Enclosure] instance.
 * @property guid The RSS `<guid>` element wrapped in an [Guid] instance.
 * @property pubDate The RSS `<pubDate>` element text content.
 * @property source The RSS `<source>` element's text content.
 * @property content The data from the RSS 1.0 Content module namespace,
 *                   or null if no data from this namespace was found.
 * @property itunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property podlove The data from the Podlove standards namespaces,
 *                   or null if no data from these namespaces were found.
 * @property googleplay The data from the Google Play namespace, or null if no data from this namespace was found.
 * @property bitlove The data from the Bitlove namespace, or null if no data from this namespace was found.
 * @property podcastindex The data from the Podcastindex namespace, or null if no data from this namespace was found.
 *
 * @since 1.0.0
 */
@Suppress("unused")
public data class Episode(
    val title: String,
    val link: String? = null,
    val description: String? = null,
    val author: String? = null,
    val categories: List<RssCategory> = emptyList(),
    val comments: String? = null,
    val enclosure: Enclosure,
    val guid: Guid? = null,
    val pubDate: TemporalAccessor? = null,
    val source: String? = null,
    val content: Content? = null,
    val itunes: EpisodeItunes? = null,
    val atom: Atom? = null,
    val podlove: EpisodePodlove? = null,
    val googleplay: EpisodeGoogleplay? = null,
    val bitlove: Bitlove? = null,
    val podcastindex: EpisodePodcastindex? = null
) {

    /** Provides a builder for the [Episode] class. */
    public companion object Factory : BuilderFactory<Episode, EpisodeBuilder> {

        /** Returns a builder implementation for building [Episode] model instances. */
        @JvmStatic
        override fun builder(): EpisodeBuilder = ValidatingEpisodeBuilder()
    }
}
