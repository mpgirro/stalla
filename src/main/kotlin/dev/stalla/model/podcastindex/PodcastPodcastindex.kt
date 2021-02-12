package dev.stalla.model.podcastindex

import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastindexBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Podcast 1.0 namespace that are valid within `<channel>` elements.
 *
 * @property locked The lock status of the podcast.
 * @property funding The funding information for the podcast.
 */
public data class PodcastPodcastindex(
    val locked: Locked? = null,
    val funding: List<Funding> = emptyList()
) {

    public companion object Factory : BuilderFactory<PodcastPodcastindex, PodcastPodcastindexBuilder> {

        /** Returns a builder implementation for building [PodcastPodcastindex] model instances. */
        @JvmStatic
        override fun builder(): PodcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
    }
}
