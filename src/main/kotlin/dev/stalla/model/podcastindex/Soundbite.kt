package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexSoundbiteBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.Episode
import dev.stalla.model.StyledDuration

/**
 * The soundbite information for the episode. Used to automatically extract soundbites from the [Episode.enclosure].
 *
 * @property startTime The timestamp at which the soundbite starts.
 * @property duration The duration of the timestamp (recommended between 15 and 120 seconds).
 * @property title A custom title for the soundbite. When null, the [Episode.title] is used.
 *
 * @since 1.0.0
 */
public data class Soundbite(
    val startTime: StyledDuration.SecondsAndFraction,
    val duration: StyledDuration.SecondsAndFraction,
    val title: String? = null
) {

    /** Provides a builder for the [Soundbite] class. */
    public companion object Factory : BuilderFactory<Soundbite, EpisodePodcastindexSoundbiteBuilder> {

        /** Returns a builder implementation for building [Soundbite] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexSoundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
    }
}
