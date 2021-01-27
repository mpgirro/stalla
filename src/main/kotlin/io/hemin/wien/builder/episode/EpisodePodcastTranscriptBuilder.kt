package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import java.util.Locale

interface EpisodePodcastTranscriptBuilder : Builder<Episode.Podcast.Transcript> {

    fun url(url: String): EpisodePodcastTranscriptBuilder

    fun type(type: Episode.Podcast.Transcript.Type): EpisodePodcastTranscriptBuilder

    fun language(language: Locale?): EpisodePodcastTranscriptBuilder

    fun rel(rel: String?): EpisodePodcastTranscriptBuilder
}
