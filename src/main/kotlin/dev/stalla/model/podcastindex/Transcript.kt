package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexTranscriptBuilder
import dev.stalla.model.BuilderFactory
import java.util.Locale

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
    val type: TranscriptType,
    val language: Locale? = null,
    val rel: String? = null
) {

    public companion object Factory : BuilderFactory<Transcript, EpisodePodcastindexTranscriptBuilder> {

        /** Returns a builder implementation for building [Transcript] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexTranscriptBuilder = ValidatingEpisodePodcastindexTranscriptBuilder()
    }
}
