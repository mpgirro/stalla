package dev.stalla.model.podcastindex

import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastindexBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Podcastindex namespace that are valid within `<channel>` elements.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][PodcastPodcastindex.Factory.builder]
 * method to obtain a [PodcastPodcastindexBuilder] instance for expressive construction instead.
 *
 * @property locked The lock status of the podcast.
 * @property funding The funding information for the podcast.
 * @property persons The person information for the podcast.
 * @property location The location information for the podcast.
 *
 * @since 1.0.0
 */
public data class PodcastPodcastindex(
    val locked: Locked? = null,
    val funding: List<Funding> = emptyList(),
    val persons: List<PodcastindexPerson> = emptyList(),
    val location: PodcastindexLocation? = null
) {

    /** Provides a builder for the [PodcastPodcastindex] class. */
    public companion object Factory : BuilderFactory<PodcastPodcastindex, PodcastPodcastindexBuilder> {

        /** Returns a builder implementation for building [PodcastPodcastindex] model instances. */
        @JvmStatic
        override fun builder(): PodcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
    }
}
