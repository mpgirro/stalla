package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.model.podcastindex.Transcript
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.util.InternalAPI
import java.util.Locale

@InternalAPI
internal class ValidatingEpisodePodcastindexTranscriptBuilder : EpisodePodcastindexTranscriptBuilder {

    private lateinit var urlValue: String
    private lateinit var typeValue: TranscriptType

    private var language: Locale? = null
    private var rel: String? = null

    override fun url(url: String): EpisodePodcastindexTranscriptBuilder = apply { this.urlValue = url }

    override fun type(type: TranscriptType): EpisodePodcastindexTranscriptBuilder = apply { this.typeValue = type }

    override fun language(language: Locale?): EpisodePodcastindexTranscriptBuilder = apply { this.language = language }

    override fun rel(rel: String?): EpisodePodcastindexTranscriptBuilder = apply { this.rel = rel }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && ::typeValue.isInitialized

    override fun build(): Transcript? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Transcript(
            url = urlValue,
            type = typeValue,
            language = language,
            rel = rel
        )
    }
}
