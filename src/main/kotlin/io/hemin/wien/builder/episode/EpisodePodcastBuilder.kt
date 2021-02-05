package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.asBuilders
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.Podcast] instances. */
interface EpisodePodcastBuilder : Builder<Episode.Podcast> {

    /**
     * Set the [EpisodePodcastChaptersBuilder] for the Podcast namespace `<chapters>` info.
     */
    fun chaptersBuilder(chaptersBuilder: EpisodePodcastChaptersBuilder): EpisodePodcastBuilder

    /**
     * Adds a [EpisodePodcastSoundbiteBuilder] for the Podcast namespace `<soundbite>` info to the list of soundbite builders.
     */
    fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastSoundbiteBuilder): EpisodePodcastBuilder

    /**
     * Adds multiple [EpisodePodcastSoundbiteBuilder] for the Podcast namespace `<soundbite>` info to the list of soundbite builders.
     */
    fun addSoundbiteBuilders(soundbiteBuilders: List<EpisodePodcastSoundbiteBuilder>): EpisodePodcastBuilder = apply {
        soundbiteBuilders.forEach(::addSoundbiteBuilder)
    }

    /**
     * Adds a [EpisodePodcastTranscriptBuilder] for the Podcast namespace `<transcript>` info to the list of transcript builders.
     */
    fun addTranscriptBuilder(transcriptBuilder: EpisodePodcastTranscriptBuilder): EpisodePodcastBuilder

    /**
     * Adds multiple [EpisodePodcastTranscriptBuilder] for the Podcast namespace `<transcript>` info to the list of transcript builders.
     */
    fun addTranscriptBuilders(transcriptBuilders: List<EpisodePodcastTranscriptBuilder>): EpisodePodcastBuilder = apply {
        transcriptBuilders.forEach(::addTranscriptBuilder)
    }

    override fun from(model: Episode.Podcast?): EpisodePodcastBuilder = whenNotNull(model) { podcast ->
        chaptersBuilder(Episode.Podcast.Chapters.builder().from(podcast.chapters))
        addSoundbiteBuilders(podcast.soundbites.asBuilders())
        addTranscriptBuilders(podcast.transcripts.asBuilders())
    }
}
