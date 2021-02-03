package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.whenNotNull
import java.time.Duration

/** Builder for constructing [Episode.Podcast.Soundbite] instances. */
interface EpisodePodcastSoundbiteBuilder : Builder<Episode.Podcast.Soundbite> {

    /** Set the starTime value. */
    fun startTime(startTime: Duration): EpisodePodcastSoundbiteBuilder

    /** Set the duration value. */
    fun duration(duration: Duration): EpisodePodcastSoundbiteBuilder

    /** Set the title value. */
    fun title(title: String?): EpisodePodcastSoundbiteBuilder

    override fun from(model: Episode.Podcast.Soundbite?): EpisodePodcastSoundbiteBuilder = whenNotNull(model) { soundbite ->
        startTime(soundbite.startTime)
        duration(soundbite.duration)
        title(soundbite.title)
    }
}
