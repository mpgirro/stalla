package dev.stalla.model

import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.builder.episode.EpisodePodcastBuilder
import dev.stalla.builder.episode.EpisodePodcastChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastTranscriptBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeBitloveBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeEnclosureBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeGuidBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastChaptersBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastSoundbiteBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastTranscriptBuilder
import dev.stalla.model.Episode.Podcast.Transcript.Type
import dev.stalla.model.content.Content
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.podlove.EpisodePodlove
import java.time.Duration
import java.time.temporal.TemporalAccessor
import java.util.Locale

/**
 * Model class for all the properties extracted by parser implementations from RSS `<item>` elements.
 *
 * @property title The RSS `<title>` field textContent.
 * @property link The RSS `<link>` field textContent.
 * @property description The RSS `<description>` field textContent.
 * @property author The RSS `<author>` field textContent
 * @property categories List of RSS `<category>` field contents.
 * @property comments The RSS `<comments>` field textContent.
 * @property enclosure The RSS `<enclosure>` element attributes wrapped in an [Episode.Enclosure] instance.
 * @property guid The RSS `<guid>` element wrapped in an [Episode.Guid] instance.
 * @property pubDate The RSS `<pubDate>` field textContent.
 * @property source The RSS `<source>` field textContent.
 * @property content The data from the Content namespace, or null if no data from this namespace was found.
 * @property iTunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property podlove The data from the Podlove standards namespaces, or null if no data from these namespaces were found.
 * @property googlePlay The data from the Google Play namespace, or null if no data from this namespace was found.
 * @property bitlove The data from the Bitlove namespace, or null if no data from this namespace was found.
 * @property podcast The data from the Podcast namespace, or null if no data from this namespace was found.
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
    val iTunes: EpisodeItunes? = null,
    val atom: Atom? = null,
    val podlove: EpisodePodlove? = null,
    val googlePlay: EpisodeGoogleplay? = null,
    val bitlove: Bitlove? = null,
    val podcast: Podcast? = null
) {

    public companion object Factory : BuilderFactory<Episode, EpisodeBuilder> {

        /** Returns a builder implementation for building [Episode] model instances. */
        @JvmStatic
        override fun builder(): EpisodeBuilder = ValidatingEpisodeBuilder()
    }

    /**
     * Model class for `<enclosure>` elements within RSS `<item>` elements.
     *
     * @property url The `url` attribute textContent of the RSS `<enclosure>` element.
     * @property length The `length` attribute textContent of the RSS `<enclosure>` element. The media length in seconds.
     * @property type The `type` attribute textContent of the RSS `<enclosure>` element.
     */
    public data class Enclosure(
        val url: String,
        val length: Long,
        val type: String
    ) {

        public companion object Factory : BuilderFactory<Enclosure, EpisodeEnclosureBuilder> {

            /** Returns a builder implementation for building [Enclosure] model instances. */
            @JvmStatic
            override fun builder(): EpisodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
        }
    }

    /**
     * Model class for `<guid>` elements within RSS `<item>` elements.
     *
     * @property guid The text content of the element.
     * @property isPermalink The boolean interpretation of the `isPermalink` attribute.
     */
    public data class Guid(
        val guid: String,
        val isPermalink: Boolean? = null
    ) {

        public companion object Factory : BuilderFactory<Guid, EpisodeGuidBuilder> {

            /** Returns a builder implementation for building [Guid] model instances. */
            @JvmStatic
            override fun builder(): EpisodeGuidBuilder = ValidatingEpisodeGuidBuilder()
        }
    }

    /**
     * Model class for data from elements of the Bitlove namespace that are valid within `<item>` elements.
     *
     * @property guid The GUID attribute for the RSS enclosure element.
     */
    public data class Bitlove(
        val guid: String
    ) {

        public companion object Factory : BuilderFactory<Bitlove, EpisodeBitloveBuilder> {

            /** Returns a builder implementation for building [Bitlove] model instances. */
            @JvmStatic
            override fun builder(): EpisodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()
        }
    }

    /**
     * Model class for data from elements of the Podcast 1.0 namespace that are valid within `<item>` elements.
     *
     * @property transcripts The transcript information for the episode.
     * @property soundbites The soundbites information for the episode.
     * @property chapters The chapters information for the episode.
     */
    public data class Podcast(
        val transcripts: List<Transcript> = emptyList(),
        val soundbites: List<Soundbite> = emptyList(),
        val chapters: Chapters? = null
    ) {

        public companion object Factory : BuilderFactory<Podcast, EpisodePodcastBuilder> {

            /** Returns a builder implementation for building [Podcast] model instances. */
            @JvmStatic
            override fun builder(): EpisodePodcastBuilder = ValidatingEpisodePodcastBuilder()
        }

        /**
         * The transcript for the episode.
         *
         * @param url The URL of the episode transcript.
         * @param type The type of transcript. One of the supported [Type]s.
         * @param language The transcript language.
         * @param rel When present and equals to `captions`, the transcript is considered to be a CC, regardless of its [type].
         */
        public data class Transcript(
            val url: String,
            val type: Type,
            val language: Locale? = null,
            val rel: String? = null
        ) {

            public companion object Factory : BuilderFactory<Transcript, EpisodePodcastTranscriptBuilder> {

                /** Returns a builder implementation for building [Transcript] model instances. */
                @JvmStatic
                override fun builder(): EpisodePodcastTranscriptBuilder = ValidatingEpisodePodcastTranscriptBuilder()
            }

            /**
             * Supported transcript types. See the
             * [reference docs](https://github.com/Podcastindex-org/podcast-namespace/blob/main/transcripts/transcripts.md).
             */
            public enum class Type(public val rawType: String) {

                /** Plain text, with no timing information. */
                PLAIN_TEXT("text/plain"),

                /** HTML, potentially with some timing information. */
                HTML("text/html"),

                /** JSON ,with full timing information. */
                JSON("application/json"),

                /** SRT, with full timing information. */
                SRT("application/srt");

                public companion object Factory {

                    public fun from(rawType: String): Type? = values().find { it.rawType == rawType }
                }
            }
        }

        /**
         * The chapters information for the episode. See the
         * [reference docs](https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md#chapters).
         *
         * @param url The URL for the chapters information.
         * @param type The MIME type of the chapters file. JSON (`application/json+chapters`) is the preferred format.
         */
        public data class Chapters(
            val url: String,
            val type: String
        ) {

            public companion object Factory : BuilderFactory<Chapters, EpisodePodcastChaptersBuilder> {

                /** Returns a builder implementation for building [Chapters] model instances. */
                @JvmStatic
                override fun builder(): EpisodePodcastChaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()
            }
        }

        /**
         * The soundbite information for the episode. Used to automatically extract soundbites from the [Episode.enclosure].
         *
         * @param startTime The timestamp at which the soundbite starts.
         * @param duration The duration of the timestamp (recommended between 15 and 120 seconds).
         * @param title A custom title for the soundbite. When null, the [Episode.title] is used.
         */
        public data class Soundbite(
            val startTime: Duration,
            val duration: Duration,
            val title: String? = null
        ) {

            public companion object Factory : BuilderFactory<Soundbite, EpisodePodcastSoundbiteBuilder> {

                /** Returns a builder implementation for building [Soundbite] model instances. */
                @JvmStatic
                override fun builder(): EpisodePodcastSoundbiteBuilder = ValidatingEpisodePodcastSoundbiteBuilder()
            }
        }
    }
}
