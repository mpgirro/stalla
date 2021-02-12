package dev.stalla.model.podcastns

import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastSoundbiteBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.Episode
import java.time.Duration

/**
 * The soundbite information for the episode. Used to automatically extract soundbites from the [Episode.enclosure].
 *
 * @param startTime The timestamp at which the soundbite starts.
 * @param duration The duration of the timestamp (recommended between 15 and 120 seconds).
 * @param title A custom title for the soundbite. When null, the [Episode.title] is used.
 */
public data class Soundbite(
    val startTime: Duration,
    val duration: Duration,
    val title: String? = null
) {

    public companion object Factory : BuilderFactory<Soundbite, EpisodePodcastSoundbiteBuilder> {

        /** Returns a builder implementation for building [Soundbite] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastSoundbiteBuilder = ValidatingEpisodePodcastSoundbiteBuilder()
    }
}
