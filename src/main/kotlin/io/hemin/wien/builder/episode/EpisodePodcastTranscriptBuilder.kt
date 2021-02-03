package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.whenNotNull
import java.util.Locale

/** Builder for constructing [Episode.Podcast.Transcript] instances. */
interface EpisodePodcastTranscriptBuilder : Builder<Episode.Podcast.Transcript> {

    /** Set the url value. */
    fun url(url: String): EpisodePodcastTranscriptBuilder

    /** Set the [Episode.Podcast.Transcript.Type] value. */
    fun type(type: Episode.Podcast.Transcript.Type): EpisodePodcastTranscriptBuilder

    /** Set the language value. */
    fun language(language: Locale?): EpisodePodcastTranscriptBuilder

    /** Set the rel value. */
    fun rel(rel: String?): EpisodePodcastTranscriptBuilder

    override fun from(model: Episode.Podcast.Transcript?): EpisodePodcastTranscriptBuilder = whenNotNull(model) { transcript ->
        url(transcript.url)
        type(transcript.type)
        language(transcript.language)
        rel(transcript.rel)
    }
}
