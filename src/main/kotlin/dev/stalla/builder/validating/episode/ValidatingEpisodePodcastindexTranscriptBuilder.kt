package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.podcastindex.Transcript
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.util.InternalAPI
import java.util.Locale

@InternalAPI
internal class ValidatingEpisodePodcastindexTranscriptBuilder : EpisodePodcastindexTranscriptBuilder {

    private var url: String? = null
    private var type: TranscriptType? = null

    private var language: Locale? = null
    private var rel: String? = null

    override fun url(url: String): EpisodePodcastindexTranscriptBuilder = apply { this.url = url }

    override fun type(type: TranscriptType): EpisodePodcastindexTranscriptBuilder = apply { this.type = type }

    override fun language(language: Locale?): EpisodePodcastindexTranscriptBuilder = apply { this.language = language }

    override fun rel(rel: String?): EpisodePodcastindexTranscriptBuilder = apply { this.rel = rel }

    override val hasEnoughDataToBuild: Boolean
        get() = url != null && type != null

    override fun build(): Transcript? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Transcript(
            url = checkRequiredProperty(::url),
            type = checkRequiredProperty(::type),
            language = language,
            rel = rel
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodePodcastindexTranscriptBuilder) return false

        if (url != other.url) return false
        if (type != other.type) return false
        if (language != other.language) return false
        if (rel != other.rel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (language?.hashCode() ?: 0)
        result = 31 * result + (rel?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String =
        "ValidatingEpisodePodcastindexTranscriptBuilder(url='$url', type=$type, language=$language, rel=$rel)"
}
