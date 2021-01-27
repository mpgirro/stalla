package io.hemin.wien.model

import io.hemin.wien.builder.episode.EpisodeBitloveBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeBitloveBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeContentBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeEnclosureBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeGooglePlayBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeGuidBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeITunesBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodePodloveBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodePodloveSimpleChapterBuilder
import io.hemin.wien.model.Episode.Podlove.SimpleChapter
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
data class Episode(
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
    val iTunes: ITunes? = null,
    val atom: Atom? = null,
    val podlove: Podlove? = null,
    val googlePlay: GooglePlay? = null,
    val bitlove: Bitlove? = null,
    val podcast: Podcast? = null
) {

    companion object Factory : BuilderFactory<Episode, EpisodeBuilder> {
        @JvmStatic override fun builder(): EpisodeBuilder = ValidatingEpisodeBuilder()
    }

    /**
     * Model class for `<enclosure>` elements within RSS `<item>` elements.
     *
     * @property url The `url` attribute textContent of the RSS `<enclosure>` element.
     * @property length The `length` attribute textContent of the RSS `<enclosure>` element. The media length in seconds.
     * @property type The `type` attribute textContent of the RSS `<enclosure>` element.
     */
    data class Enclosure(
        val url: String,
        val length: Long,
        val type: String
    ) {
        companion object Factory : BuilderFactory<Enclosure, EpisodeEnclosureBuilder> {
            @JvmStatic override fun builder(): EpisodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
        }
    }

    /**
     * Model class for `<guid>` elements within RSS `<item>` elements.
     *
     * @property guid The text content of the element.
     * @property isPermalink The boolean interpretation of the `isPermalink` attribute.
     */
    data class Guid(
        val guid: String,
        val isPermalink: Boolean? = null
    ) {
        companion object Factory : BuilderFactory<Guid, EpisodeGuidBuilder> {
            @JvmStatic override fun builder(): EpisodeGuidBuilder = ValidatingEpisodeGuidBuilder()
        }
    }

    /**
     * Model class for data from elements of the Content namespace that are valid within `<item>` elements.
     *
     * @property encoded The text content of the `<content:encoded>` element.
     */
    data class Content(
        val encoded: String
    ) {
        companion object Factory : BuilderFactory<Content, EpisodeContentBuilder> {
            @JvmStatic override fun builder(): EpisodeContentBuilder = ValidatingEpisodeContentBuilder()
        }
    }

    /**
     * Model class for data from elements of the iTunes namespace that are valid within `<item>` elements.
     *
     * @property title The `<itunes:title>` field text content.
     * @property duration The `<itunes:duration>` field text content.
     * @property image The data from the `<itunes:image>` element as an [HrefOnlyImage].
     * @property explicit The logical value of the `<itunes:explicit>` field's text content.
     * @property block The logical value of the `<itunes:block>` field's text content.
     * @property season The numeric value of the `<itunes:season>` field's text content.
     * @property episode The numeric value of the `<itunes:episode>` field's text content.
     * @property episodeType The `<itunes:episodeType>` field text content.
     */
    data class ITunes(
        override val title: String? = null,
        val duration: String? = null,
        override val image: HrefOnlyImage? = null,
        val explicit: Boolean? = null,
        override val block: Boolean,
        val season: Int? = null,
        val episode: Int? = null,
        val episodeType: EpisodeType? = null,
        override val author: String? = null,
        override val subtitle: String? = null,
        override val summary: String? = null
    ) : ITunesBase {

        companion object Factory : BuilderFactory<ITunes, EpisodeITunesBuilder> {
            @JvmStatic override fun builder(): EpisodeITunesBuilder = ValidatingEpisodeITunesBuilder()
        }

        /**
         * Enum model for the defined values encountered within the
         * `<itunes:episodeType>` element within a `<item>` element.
         *
         * @property type The string representation of the enum instance.
         */
        enum class EpisodeType(val type: String) {

            /** Type describing a bonus episode. */
            BONUS("bonus"),

            /** Type describing a full episode. */
            FULL("full"),

            /** Type describing a trailer episode. */
            TRAILER("trailer");

            companion object {
                /**
                 * Factory method for the instance of the Enum matching the [type] parameter.
                 *
                 * @param type The string representation of the Enum instance.
                 * @return The Enum instance matching [type], or null if not matching instance exists.
                 */
                fun of(type: String?): EpisodeType? = type?.let {
                    values().find { t -> t.type == it.toLowerCase() }
                }
            }
        }
    }

    /**
     * Model class for data from elements of the Google Play namespace that are valid within `<item>` elements.
     *
     * @property description The `<googleplay:description>` field text content.
     * @property explicit The logical value of the `<googleplay:explicit>` field's text content.
     * @property block The logical value of the `<googleplay:block>` field's text content.
     * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
     */
    data class GooglePlay(
        override val description: String? = null,
        override val explicit: Boolean? = null,
        override val block: Boolean,
        override val image: HrefOnlyImage? = null
    ) : GooglePlayBase {
        companion object Factory : BuilderFactory<GooglePlay, EpisodeGooglePlayBuilder> {
            @JvmStatic override fun builder(): EpisodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()
        }
    }

    /**
     * Model class for data from elements of namespaces from the Podlove
     * standards family that are valid within `<item>` elements.
     *
     * @property simpleChapters List of data from the `<psc:chapter>` elements as [SimpleChapter] instances.
     */
    data class Podlove(
        val simpleChapters: List<SimpleChapter>
    ) {

        companion object Factory : BuilderFactory<Podlove, EpisodePodloveBuilder> {
            @JvmStatic override fun builder(): EpisodePodloveBuilder = ValidatingEpisodePodloveBuilder()
        }

        /**
         * Model class for data from `<psc:chapter>` elements of the Podlove
         * Simple Chapter namespace that are valid within `<item>` elements.
         *
         * @property start The value of the chapter's `start` attribute.
         * @property title The value of the chapter's `title` attribute.
         * @property href The value of the chapter's `href` attribute.
         * @property image The value of the chapter's `image` attribute.
         */
        data class SimpleChapter(
            val start: String,
            val title: String,
            val href: String? = null,
            val image: String? = null
        ) {
            companion object Factory : BuilderFactory<SimpleChapter, EpisodePodloveSimpleChapterBuilder> {
                @JvmStatic override fun builder(): EpisodePodloveSimpleChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
            }
        }
    }

    /**
     * Model class for data from elements of the Bitlove namespace that are valid within `<item>` elements.
     *
     * @property guid The GUID attribute for the RSS enclosure element.
     */
    data class Bitlove(
        val guid: String
    ) {
        companion object Factory : BuilderFactory<Bitlove, EpisodeBitloveBuilder> {
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
    data class Podcast(
        val transcripts: List<Transcript> = emptyList(),
        val soundbites: List<Soundbite> = emptyList(),
        val chapters: Chapters? = null
    ) {

        /**
         * The transcript for the episode.
         *
         * @param url The URL of the episode transcript.
         * @param type The type of transcript. One of the supported [Type]s.
         * @param language The transcript language.
         * @param rel When present and equals to `captions`, the transcript is considered to be a CC, regardless of its [type].
         */
        data class Transcript(
            val url: String,
            val type: Type,
            val language: Locale? = null,
            val rel: String? = null
        ) {

            /**
             * Supported transcript types. See the
             * [reference docs](https://github.com/Podcastindex-org/podcast-namespace/blob/main/transcripts/transcripts.md).
             */
            enum class Type(val rawType: String) {
                /** Plain text, with no timing information. */
                PLAIN_TEXT("text/plain"),

                /** HTML, potentially with some timing information. */
                HTML("text/html"),

                /** JSON ,with full timing information. */
                JSON("application/json"),

                /** SRT, with full timing information. */
                SRT("application/srt");

                companion object {

                    fun from(rawType: String): Type? = values().find { it.rawType == rawType }
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
        data class Chapters(
            val url: String,
            val type: String
        )

        /**
         * The soundbite information for the episode. Used to automatically extract soundbites from the [Episode.enclosure].
         *
         * @param startTime The timestamp at which the soundbite starts.
         * @param duration The duration of the timestamp (recommended between 15 and 120 seconds).
         * @param title A custom title for the soundbite. When null, the [Episode.title] is used.
         */
        data class Soundbite(
            val startTime: Duration,
            val duration: Duration,
            val title: String? = null
        )
    }
}
