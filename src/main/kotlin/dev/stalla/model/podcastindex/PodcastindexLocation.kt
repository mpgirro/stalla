package dev.stalla.model.podcastindex

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexLocationBuilder
import dev.stalla.model.BuilderFactory

/**
 * The location information of editorial focus for a podcast's or episode's content
 * (i.e. "what place is this podcast/episode about?").
 *
 * Direct instantiation in Java is discouraged. Use the [builder][PodcastindexLocation.Factory.builder]
 * method to obtain a [PodcastindexLocation] instance for expressive construction instead.
 *
 * @property name Human-readable place name.
 * @property geo The geographic location of this place, based on a "geo" notation URI.
 * @property osm The Open Street Map identifier of this place, given using the OSM notation.
 *
 * @since 1.1.0
 */
public data class PodcastindexLocation(
    val name: String,
    val geo: GeographicLocation?,
    val osm: OpenStreetMapElement?
) {
    /** Provides a builder for the [PodcastindexLocation] class. */
    public companion object Factory : BuilderFactory<PodcastindexLocation, PodcastindexLocationBuilder> {

        /** Returns a builder implementation for building [PodcastindexLocation] model instances. */
        @JvmStatic
        override fun builder(): PodcastindexLocationBuilder = ValidatingPodcastindexLocationBuilder()
    }
}
