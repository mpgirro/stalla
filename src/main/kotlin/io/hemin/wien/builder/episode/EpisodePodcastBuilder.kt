package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

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

    /**
     * The builders for the Podcast namespace `<transcript>` info.
     */
    fun addTranscriptBuilder(transcriptBuilder: EpisodePodcastTranscriptBuilder): EpisodePodcastBuilder
}
