package dev.stalla.builder

import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [PodcastindexLocation] instances.
 *
 * @since 1.1.0
 */
public interface PodcastindexLocationBuilder : Builder<PodcastindexLocation> {

    /** Set the name value. */
    public fun name(name: String): PodcastindexLocationBuilder

    /** Set the geo value. */
    public fun geo(geo: GeoLocation?): PodcastindexLocationBuilder

    /** Set the osm value. */
    public fun osm(osm: OpenStreetMapFeature?): PodcastindexLocationBuilder

    override fun applyFrom(prototype: PodcastindexLocation?): PodcastindexLocationBuilder =
        whenNotNull(prototype) { location ->
            name(location.name)
            geo(GeoLocation.builder().applyFrom(location.geo).build())
            osm(OpenStreetMapFeature.builder().applyFrom(location.osm).build())
        }
}
