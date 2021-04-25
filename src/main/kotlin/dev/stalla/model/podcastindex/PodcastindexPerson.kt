package dev.stalla.model.podcastindex

import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexPersonBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexSeasonBuilder
import dev.stalla.model.BuilderFactory

/**
 * TODO.
 *
 * @property name TODO.
 * @property role TODO.
 * @property group TODO.
 * @property img TODO.
 * @property href TODO.
 *
 * @since 1.1.0
 */
public data class PodcastindexPerson(
    val name: String,
    val role: String?,
    val group: String?,
    val img: String?,
    val href: String?
) {
    /** Provides a builder for the [PodcastindexPerson] class. */
    public companion object Factory : BuilderFactory<PodcastindexPerson, PodcastindexPersonBuilder> {

        /** Returns a builder implementation for building [PodcastindexPerson] model instances. */
        @JvmStatic
        override fun builder(): PodcastindexPersonBuilder = ValidatingPodcastindexPersonBuilder()
    }
}
