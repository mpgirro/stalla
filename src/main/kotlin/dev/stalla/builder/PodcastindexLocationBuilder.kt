package dev.stalla.builder

import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.util.whenNotNull

public interface PodcastindexLocationBuilder : Builder<PodcastindexLocation> {

    public fun name(name: String): PodcastindexLocationBuilder

    public fun geo(geo: GeoLocation?): PodcastindexLocationBuilder

    public fun osm(osm: OpenStreetMapFeature?): PodcastindexLocationBuilder

    override fun applyFrom(prototype: PodcastindexLocation?): PodcastindexLocationBuilder =
        whenNotNull(prototype) { location ->
            name(location.name)
            geo(GeoLocation.builder().applyFrom(location.geo).build()!!)
            // TODO osm or osmBuilder ?!
        }

}
