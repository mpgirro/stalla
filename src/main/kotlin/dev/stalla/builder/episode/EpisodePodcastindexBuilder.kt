package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [EpisodePodcastindex] instances. */
public interface EpisodePodcastindexBuilder : Builder<EpisodePodcastindex> {

    /**
     * Set the [EpisodePodcastindexChaptersBuilder] for the Podcastindex namespace `<chapters>` info.
     */
    public fun chaptersBuilder(chaptersBuilder: EpisodePodcastindexChaptersBuilder): EpisodePodcastindexBuilder

    /**
     * Adds the [EpisodePodcastindexSoundbiteBuilder] for the
     * `<podcast:soundbite>` info to the list of soundbite builders.
     */
    public fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastindexSoundbiteBuilder): EpisodePodcastindexBuilder

    /**
     * Adds all of the [EpisodePodcastindexSoundbiteBuilder] for the
     * `<podcast:soundbite>` info to the list of soundbite builders.
     */
    public fun addAllSoundbiteBuilder(
        soundbiteBuilders: List<EpisodePodcastindexSoundbiteBuilder>
    ): EpisodePodcastindexBuilder = apply {
        soundbiteBuilders.forEach(::addSoundbiteBuilder)
    }

    /**
     * Adds the [EpisodePodcastindexTranscriptBuilder] for the
     * `<podcast:transcript>` info to the list of transcript builders.
     */
    public fun addTranscriptBuilder(
        transcriptBuilder: EpisodePodcastindexTranscriptBuilder
    ): EpisodePodcastindexBuilder

    /**
     * Adds all of the [EpisodePodcastindexTranscriptBuilder] for the
     * `<podcast:transcript>` info to the list of transcript builders.
     */
    public fun addAllTranscriptBuilder(
        transcriptBuilders: List<EpisodePodcastindexTranscriptBuilder>
    ): EpisodePodcastindexBuilder = apply {
        transcriptBuilders.forEach(::addTranscriptBuilder)
    }

    override fun applyFrom(prototype: EpisodePodcastindex?): EpisodePodcastindexBuilder {
        return whenNotNull(prototype) { podcast ->
            chaptersBuilder(Chapters.builder().applyFrom(podcast.chapters))
            addAllSoundbiteBuilder(podcast.soundbites.asBuilders())
            addAllTranscriptBuilder(podcast.transcripts.asBuilders())
        }
    }
}
