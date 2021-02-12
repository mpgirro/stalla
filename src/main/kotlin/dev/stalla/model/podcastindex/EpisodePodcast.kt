package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Podcast 1.0 namespace that are valid within `<item>` elements.
 *
 * @property transcripts The transcript information for the episode.
 * @property soundbites The soundbites information for the episode.
 * @property chapters The chapters information for the episode.
 */
public data class EpisodePodcast(
    val transcripts: List<Transcript> = emptyList(),
    val soundbites: List<Soundbite> = emptyList(),
    val chapters: Chapters? = null
) {

    public companion object Factory : BuilderFactory<EpisodePodcast, EpisodePodcastBuilder> {

        /** Returns a builder implementation for building [EpisodePodcast] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastBuilder = ValidatingEpisodePodcastBuilder()
    }
}
