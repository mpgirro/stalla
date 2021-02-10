package dev.stalla.model

import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.builder.podcast.PodcastPodcastBuilder
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastLockedBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastFundingBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastLockedBuilder
import dev.stalla.model.feedpress.Feedpress
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.rss.RssCategory
import dev.stalla.model.rss.RssImage
import java.time.temporal.TemporalAccessor

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
 * @property iTunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property fyyd The data from the Fyyd namespace, or null if no data from this namespace was found.
 * @property feedpress The data from the Feedpress namespace, or null if no data from this namespace was found.
 * @property googlePlay The data from the Google Play namespace, or null if no data from this namespace was found.
 * @property categories The RSS feed categories, if any.
 * @property podcast The data from the Podcast namespace, or null if no data from this namespace was found.
 */
public data class Podcast(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: TemporalAccessor? = null,
    val lastBuildDate: TemporalAccessor? = null,
    val language: String,
    val generator: String? = null,
    val copyright: String? = null,
    val docs: String? = null,
    val managingEditor: String? = null,
    val webMaster: String? = null,
    val ttl: Int? = null,
    val image: RssImage? = null,
    val episodes: List<Episode>,
    val iTunes: PodcastItunes? = null,
    val atom: Atom? = null,
    val fyyd: Fyyd? = null,
    val feedpress: Feedpress? = null,
    val googlePlay: PodcastGoogleplay? = null,
    val categories: List<RssCategory> = emptyList(),
    val podcast: Podcast? = null
) {

    public companion object Factory : BuilderFactory<dev.stalla.model.Podcast, PodcastBuilder> {

        /** Returns a builder implementation for building [dev.stalla.model.Podcast] model instances. */
        @JvmStatic
        override fun builder(): PodcastBuilder = ValidatingPodcastBuilder()
    }

    /**
     * Model class for data from elements of the Podcast 1.0 namespace that are valid within `<channel>` elements.
     *
     * @property locked The lock status of the podcast.
     * @property funding The funding information for the podcast.
     */
    public data class Podcast(
        val locked: Locked? = null,
        val funding: List<Funding> = emptyList()
    ) {

        public companion object Factory : BuilderFactory<Podcast, PodcastPodcastBuilder> {

            /** Returns a builder implementation for building [Podcast] model instances. */
            @JvmStatic
            override fun builder(): PodcastPodcastBuilder = ValidatingPodcastPodcastBuilder()
        }

        /**
         * The lock status of the podcast. Tells other podcast platforms whether they are allowed to
         * import this feed into their systems.
         *
         * @param owner An email address that can be used to verify ownership when moving hosting platforms.
         * @param locked When `true`, the podcast cannot be transferred to a new hosting platform.
         */
        public data class Locked(
            val owner: String,
            val locked: Boolean
        ) {

            public companion object Factory : BuilderFactory<Locked, PodcastPodcastLockedBuilder> {

                /** Returns a builder implementation for building [Locked] model instances. */
                @JvmStatic
                override fun builder(): PodcastPodcastLockedBuilder = ValidatingPodcastPodcastLockedBuilder()
            }
        }

        /**
         * The funding information for the podcast.
         *
         * @param url The URL where listeners can find funding information for the podcast.
         * @param message The recommended CTA text to show users for the funding link.
         */
        public data class Funding(
            val url: String,
            val message: String
        ) {

            public companion object Factory : BuilderFactory<Funding, PodcastPodcastFundingBuilder> {

                /** Returns a builder implementation for building [Funding] model instances. */
                @JvmStatic
                override fun builder(): PodcastPodcastFundingBuilder = ValidatingPodcastPodcastFundingBuilder()
            }
        }
    }
}
