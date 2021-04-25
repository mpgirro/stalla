package dev.stalla.builder.fake

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.PodcastindexLocation

internal class FakePodcastindexLocationBuilder : FakeBuilder<PodcastindexLocation>(), PodcastindexLocationBuilder {

    var name: String? = null
    var geo: GeoLocation? = null
    var osm: OpenStreetMapFeature? = null

    override fun name(name: String): PodcastindexLocationBuilder = apply { this.name = name }

    override fun geo(geo: GeoLocation?): PodcastindexLocationBuilder = apply { this.geo = geo }

    override fun osm(osm: OpenStreetMapFeature?): PodcastindexLocationBuilder = apply { this.osm = osm }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakePodcastindexLocationBuilder

        if (name != other.name) return false
        if (geo != other.geo) return false
        if (osm != other.osm) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (geo?.hashCode() ?: 0)
        result = 31 * result + (osm?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "FakePodcastindexLocationBuilder(name=$name, geo=$geo, osm=$osm)"
    }
}
