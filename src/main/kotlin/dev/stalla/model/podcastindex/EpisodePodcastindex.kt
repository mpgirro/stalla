package dev.stalla.model.podcastindex

import dev.stalla.builder.episode.EpisodePodcastindexBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Podcastindex namespace that are valid within `<item>` elements.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][EpisodePodcastindex.Factory.builder]
 * method to obtain an [EpisodePodcastindexBuilder] instance for expressive construction instead.
 *
 * @property transcripts The transcript information for the episode.
 * @property soundbites The soundbites information for the episode.
 * @property chapters The chapters information for the episode.
 * @property persons The person information for the episode.
 * @property location The location information for the episode.
 * @property season The season information for the episode.
 * @property episode The episode information for the episode.
 *
 * @since 1.0.0
 */
public data class EpisodePodcastindex(
    val transcripts: List<Transcript> = emptyList(),
    val soundbites: List<Soundbite> = emptyList(),
    val chapters: Chapters? = null,
    val persons: List<PodcastindexPerson> = emptyList(),
    val location: PodcastindexLocation? = null,
    val season: PodcastindexSeason? = null,
    val episode: PodcastindexEpisode? = null
) {

    /** Provides a builder for the [EpisodePodcastindex] class. */
    public companion object Factory : BuilderFactory<EpisodePodcastindex, EpisodePodcastindexBuilder> {

        /** Returns a builder implementation for building [EpisodePodcastindex] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
    }
}
