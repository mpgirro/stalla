package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodcastTranscriptBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.Episode
import java.util.Locale

internal class FakeEpisodePodcastTranscriptBuilder : FakeBuilder<Episode.Podcast.Transcript>(), EpisodePodcastTranscriptBuilder {

    var url: String? = null
    var type: Episode.Podcast.Transcript.Type? = null
    var language: Locale? = null
    var rel: String? = null

    override fun url(url: String): EpisodePodcastTranscriptBuilder = apply { this.url = url }

    override fun type(type: Episode.Podcast.Transcript.Type): EpisodePodcastTranscriptBuilder = apply { this.type = type }

    override fun language(language: Locale?): EpisodePodcastTranscriptBuilder = apply { this.language = language }

    override fun rel(rel: String?): EpisodePodcastTranscriptBuilder = apply { this.rel = rel }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodePodcastTranscriptBuilder) return false

        if (url != other.url) return false
        if (type != other.type) return false
        if (language != other.language) return false
        if (rel != other.rel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url?.hashCode() ?: 0
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (language?.hashCode() ?: 0)
        result = 31 * result + (rel?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakeEpisodePodcastTranscriptBuilder(url=$url, type=$type, language=$language, rel=$rel)"
}
