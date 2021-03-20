package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Transcript
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.util.whenNotNull
import java.util.Locale

/**
 * Builder for constructing [Transcript] instances.
 *
 * @since 1.0.0
 */
public interface EpisodePodcastindexTranscriptBuilder : Builder<Transcript> {

    /** Set the url value. */
    public fun url(url: String): EpisodePodcastindexTranscriptBuilder

    /** Set the [TranscriptType] value. */
    public fun type(type: TranscriptType): EpisodePodcastindexTranscriptBuilder

    /** Set the language value. */
    public fun language(language: Locale?): EpisodePodcastindexTranscriptBuilder

    /** Set the rel value. */
    public fun rel(rel: String?): EpisodePodcastindexTranscriptBuilder

    override fun applyFrom(prototype: Transcript?): EpisodePodcastindexTranscriptBuilder =
        whenNotNull(prototype) { transcript ->
            url(transcript.url)
            type(transcript.type)
            language(transcript.language)
            rel(transcript.rel)
        }
}
