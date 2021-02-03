package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.Podcast] instances. */
interface EpisodePodcastBuilder : Builder<Episode.Podcast> {

    /**
     * The builder for the Podcast namespace `<chapters>` info.
     */
    fun chaptersBuilder(chaptersBuilder: EpisodePodcastChaptersBuilder): EpisodePodcastBuilder

    /**
     * The builders for the Podcast namespace `<soundbite>` info.
     */
    fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastSoundbiteBuilder): EpisodePodcastBuilder

    fun addSoundbiteBuilders(soundbiteBuilders: List<EpisodePodcastSoundbiteBuilder>): EpisodePodcastBuilder = apply {
        soundbiteBuilders.forEach { soundbite -> addSoundbiteBuilder(soundbite) }
    }

    /**
     * The builders for the Podcast namespace `<transcript>` info.
     */
    fun addTranscriptBuilder(transcriptBuilder: EpisodePodcastTranscriptBuilder): EpisodePodcastBuilder

    fun addTranscriptBuilders(transcriptBuilders: List<EpisodePodcastTranscriptBuilder>): EpisodePodcastBuilder = apply {
        transcriptBuilders.forEach { transcript -> addTranscriptBuilder(transcript) }
    }

    override fun from(model: Episode.Podcast?): EpisodePodcastBuilder = whenNotNull(model) { podcast ->
        chaptersBuilder(Episode.Podcast.Chapters.builder().from(podcast.chapters))
        addSoundbiteBuilders(podcast.soundbites.map { soundbite -> Episode.Podcast.Soundbite.builder().from(soundbite) })
        addTranscriptBuilders(podcast.transcripts.map { transcript -> Episode.Podcast.Transcript.builder().from(transcript) })
    }
}
