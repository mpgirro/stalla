package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastTranscriptBuilder
import dev.stalla.model.Episode
import java.util.Locale

internal class ValidatingEpisodePodcastTranscriptBuilder : EpisodePodcastTranscriptBuilder {

    private lateinit var urlValue: String
    private lateinit var typeValue: Episode.Podcast.Transcript.Type

    private var language: Locale? = null
    private var rel: String? = null

    override fun url(url: String): EpisodePodcastTranscriptBuilder = apply { this.urlValue = url }

    override fun type(type: Episode.Podcast.Transcript.Type): EpisodePodcastTranscriptBuilder = apply { this.typeValue = type }

    override fun language(language: Locale?): EpisodePodcastTranscriptBuilder = apply { this.language = language }

    override fun rel(rel: String?): EpisodePodcastTranscriptBuilder = apply { this.rel = rel }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && ::typeValue.isInitialized

    override fun build(): Episode.Podcast.Transcript? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode.Podcast.Transcript(urlValue, typeValue, language, rel)
    }
}
