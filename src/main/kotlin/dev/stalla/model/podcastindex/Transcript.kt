package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexTranscriptBuilder
import dev.stalla.model.BuilderFactory
import java.util.Locale

/**
 * The transcript for the episode.
 *
 * Direct instantiation from Java is discouraged. Use the [builder][Transcript.Factory.builder] method
 * to obtain an [EpisodePodcastindexTranscriptBuilder] instance for expressive construction instead.
 *
 * @property url The URL of the episode transcript.
 * @property type The type of transcript. One of the supported [TranscriptType]s.
 * @property language The transcript language.
 * @property rel When present and equals to `captions`, the transcript is considered to be a CC,
 *               regardless of its [type].
 *
 * @since 1.0.0
 */
public data class Transcript(
    val url: String,
    val type: TranscriptType,
    val language: Locale? = null,
    val rel: String? = null
) {

    /** Provides a builder for the [Transcript] class. */
    public companion object Factory : BuilderFactory<Transcript, EpisodePodcastindexTranscriptBuilder> {

        /** Returns a builder implementation for building [Transcript] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexTranscriptBuilder = ValidatingEpisodePodcastindexTranscriptBuilder()
    }
}
