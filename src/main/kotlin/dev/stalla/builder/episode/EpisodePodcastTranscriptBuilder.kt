package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastns.Transcript
import dev.stalla.model.podcastns.TranscriptType
import dev.stalla.util.whenNotNull
import java.util.Locale

/** Builder for constructing [Transcript] instances. */
public interface EpisodePodcastTranscriptBuilder : Builder<Transcript> {

    /** Set the url value. */
    public fun url(url: String): EpisodePodcastTranscriptBuilder

    /** Set the [TranscriptType] value. */
    public fun type(type: TranscriptType): EpisodePodcastTranscriptBuilder

    /** Set the language value. */
    public fun language(language: Locale?): EpisodePodcastTranscriptBuilder

    /** Set the rel value. */
    public fun rel(rel: String?): EpisodePodcastTranscriptBuilder

    override fun from(model: Transcript?): EpisodePodcastTranscriptBuilder = whenNotNull(model) { transcript ->
        url(transcript.url)
        type(transcript.type)
        language(transcript.language)
        rel(transcript.rel)
    }
}
