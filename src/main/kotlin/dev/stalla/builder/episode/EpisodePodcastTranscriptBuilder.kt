package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Episode
import dev.stalla.util.whenNotNull
import java.util.Locale

/** Builder for constructing [Episode.Podcast.Transcript] instances. */
public interface EpisodePodcastTranscriptBuilder : Builder<Episode.Podcast.Transcript> {

    /** Set the url value. */
    public fun url(url: String): EpisodePodcastTranscriptBuilder

    /** Set the [Episode.Podcast.Transcript.Type] value. */
    public fun type(type: Episode.Podcast.Transcript.Type): EpisodePodcastTranscriptBuilder

    /** Set the language value. */
    public fun language(language: Locale?): EpisodePodcastTranscriptBuilder

    /** Set the rel value. */
    public fun rel(rel: String?): EpisodePodcastTranscriptBuilder

    override fun from(model: Episode.Podcast.Transcript?): EpisodePodcastTranscriptBuilder = whenNotNull(model) { transcript ->
        url(transcript.url)
        type(transcript.type)
        language(transcript.language)
        rel(transcript.rel)
    }
}
