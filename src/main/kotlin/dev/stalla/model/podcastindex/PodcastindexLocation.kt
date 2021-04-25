package dev.stalla.model.podcastindex

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexLocationBuilder
import dev.stalla.model.BuilderFactory

/**
 * TODO.
 *
 * @property name Human-readable place name.
 * @property geo TODO.
 * @property osm TODO.
 *
 * @since 1.1.0
 */
public data class PodcastindexLocation(
    val name: String,
    val geo: GeoLocation?,
    val osm: OpenStreetMapFeature?
) {
    /** Provides a builder for the [PodcastindexLocation] class. */
    public companion object Factory : BuilderFactory<PodcastindexLocation, PodcastindexLocationBuilder> {

        /** Returns a builder implementation for building [PodcastindexLocation] model instances. */
        @JvmStatic
        override fun builder(): PodcastindexLocationBuilder = ValidatingPodcastindexLocationBuilder()
    }
}
