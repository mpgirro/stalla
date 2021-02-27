package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Podcast 1.0 namespace that are valid within `<item>` elements.
 *
 * @property transcripts The transcript information for the episode.
 * @property soundbites The soundbites information for the episode.
 * @property chapters The chapters information for the episode.
 */
public data class EpisodePodcastindex(
    val transcripts: List<Transcript> = emptyList(),
    val soundbites: List<Soundbite> = emptyList(),
    val chapters: Chapters? = null
) {

    /** Provides a builder for the [EpisodePodcastindex] class. */
    public companion object Factory : BuilderFactory<EpisodePodcastindex, EpisodePodcastindexBuilder> {

        /** Returns a builder implementation for building [EpisodePodcastindex] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
    }
}
