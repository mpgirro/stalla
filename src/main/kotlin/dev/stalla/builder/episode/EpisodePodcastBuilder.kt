package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Episode
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [Episode.Podcast] instances. */
public interface EpisodePodcastBuilder : Builder<EpisodePodcastindex> {

    /**
     * Set the [EpisodePodcastChaptersBuilder] for the Podcast namespace `<chapters>` info.
     */
    public fun chaptersBuilder(chaptersBuilder: EpisodePodcastChaptersBuilder): EpisodePodcastBuilder

    /**
     * Adds a [EpisodePodcastSoundbiteBuilder] for the Podcast namespace `<soundbite>` info to the list of soundbite builders.
     */
    public fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastSoundbiteBuilder): EpisodePodcastBuilder

    /**
     * Adds multiple [EpisodePodcastSoundbiteBuilder] for the Podcast namespace `<soundbite>` info to the list of soundbite builders.
     */
    public fun addSoundbiteBuilders(soundbiteBuilders: List<EpisodePodcastSoundbiteBuilder>): EpisodePodcastBuilder = apply {
        soundbiteBuilders.forEach(::addSoundbiteBuilder)
    }

    /**
     * Adds a [EpisodePodcastTranscriptBuilder] for the Podcast namespace `<transcript>` info to the list of transcript builders.
     */
    public fun addTranscriptBuilder(transcriptBuilder: EpisodePodcastTranscriptBuilder): EpisodePodcastBuilder

    /**
     * Adds multiple [EpisodePodcastTranscriptBuilder] for the Podcast namespace `<transcript>` info to the list of transcript builders.
     */
    public fun addTranscriptBuilders(transcriptBuilders: List<EpisodePodcastTranscriptBuilder>): EpisodePodcastBuilder = apply {
        transcriptBuilders.forEach(::addTranscriptBuilder)
    }

    override fun from(model: EpisodePodcastindex?): EpisodePodcastBuilder = whenNotNull(model) { podcast ->
        chaptersBuilder(Chapters.builder().from(podcast.chapters))
        addSoundbiteBuilders(podcast.soundbites.asBuilders())
        addTranscriptBuilders(podcast.transcripts.asBuilders())
    }
}
