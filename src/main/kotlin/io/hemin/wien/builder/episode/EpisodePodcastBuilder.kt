package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.podcast.PodcastPodcastBuilder
import io.hemin.wien.builder.podcast.PodcastPodcastFundingBuilder
import io.hemin.wien.builder.podcast.PodcastPodcastLockedBuilder
import io.hemin.wien.model.Episode

internal interface EpisodePodcastBuilder : Builder<Episode.Podcast> {

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
