package dev.stalla.model.podcastindex

import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexPersonBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexSeasonBuilder
import dev.stalla.model.BuilderFactory

/**
 * TODO.
 *
 * @property name The full name or alias of the person.
 * @property role The role the person serves on the show or episode - this should be a reference to an official role within the [Podcast Taxonomy Project](https://podcasttaxonomy.com) list.
 * @property group Should be an official group within the [Podcast Taxonomy Project](https://podcasttaxonomy.com) list.
 * @property img The url of a picture or avatar of the person.
 * @property href The url to a relevant resource of information about the person, such as a homepage or third-party profile platform.
 *
 * @see https://podcasttaxonomy.com
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
