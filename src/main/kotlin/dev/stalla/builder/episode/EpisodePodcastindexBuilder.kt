package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [EpisodePodcastindex] instances. */
public interface EpisodePodcastindexBuilder : Builder<EpisodePodcastindex> {

    /**
     * Set the [EpisodePodcastindexChaptersBuilder] for the Podcast namespace `<chapters>` info.
     */
    public fun chaptersBuilder(chaptersBuilder: EpisodePodcastindexChaptersBuilder): EpisodePodcastindexBuilder

    /**
     * Adds a [EpisodePodcastindexSoundbiteBuilder] for the Podcast namespace `<soundbite>` info to the list of soundbite builders.
     */
    public fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastindexSoundbiteBuilder): EpisodePodcastindexBuilder

    /**
     * Adds multiple [EpisodePodcastindexSoundbiteBuilder] for the Podcast namespace `<soundbite>` info to the list of soundbite builders.
     */
    public fun addSoundbiteBuilders(soundbiteBuilders: List<EpisodePodcastindexSoundbiteBuilder>): EpisodePodcastindexBuilder = apply {
        soundbiteBuilders.forEach(::addSoundbiteBuilder)
    }

    /**
     * Adds a [EpisodePodcastindexTranscriptBuilder] for the Podcast namespace `<transcript>` info to the list of transcript builders.
     */
    public fun addTranscriptBuilder(transcriptBuilder: EpisodePodcastindexTranscriptBuilder): EpisodePodcastindexBuilder

    /**
     * Adds multiple [EpisodePodcastindexTranscriptBuilder] for the Podcast namespace `<transcript>` info to the list of transcript builders.
     */
    public fun addTranscriptBuilders(transcriptBuilders: List<EpisodePodcastindexTranscriptBuilder>): EpisodePodcastindexBuilder = apply {
        transcriptBuilders.forEach(::addTranscriptBuilder)
    }

    override fun from(model: EpisodePodcastindex?): EpisodePodcastindexBuilder = whenNotNull(model) { podcast ->
        chaptersBuilder(Chapters.builder().from(podcast.chapters))
        addSoundbiteBuilders(podcast.soundbites.asBuilders())
        addTranscriptBuilders(podcast.transcripts.asBuilders())
    }
}
