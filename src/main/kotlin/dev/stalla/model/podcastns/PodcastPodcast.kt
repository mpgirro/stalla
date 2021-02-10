package dev.stalla.model.podcastns

import dev.stalla.builder.podcast.PodcastPodcastBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Podcast 1.0 namespace that are valid within `<channel>` elements.
 *
 * @property locked The lock status of the podcast.
 * @property funding The funding information for the podcast.
 */
public data class PodcastPodcast(
    val locked: Locked? = null,
    val funding: List<Funding> = emptyList()
) {

    public companion object Factory : BuilderFactory<PodcastPodcast, PodcastPodcastBuilder> {

        /** Returns a builder implementation for building [PodcastPodcast] model instances. */
        @JvmStatic
        override fun builder(): PodcastPodcastBuilder = ValidatingPodcastPodcastBuilder()
    }

}
