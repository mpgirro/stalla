package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import java.time.Duration

interface EpisodePodcastSoundbiteBuilder : Builder<Episode.Podcast.Soundbite> {

    fun startTime(startTime: Duration): EpisodePodcastSoundbiteBuilder

    fun duration(duration: Duration): EpisodePodcastSoundbiteBuilder

    fun title(title: String?): EpisodePodcastSoundbiteBuilder
}
