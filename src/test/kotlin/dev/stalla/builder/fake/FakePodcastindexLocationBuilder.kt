package dev.stalla.builder.fake

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.model.podcastindex.GeographicLocation
import dev.stalla.model.podcastindex.OpenStreetMapElement
import dev.stalla.model.podcastindex.PodcastindexLocation

internal class FakePodcastindexLocationBuilder : FakeBuilder<PodcastindexLocation>(), PodcastindexLocationBuilder {

    var name: String? = null
    var geo: GeographicLocation? = null
    var osm: OpenStreetMapElement? = null

    override fun name(name: String): PodcastindexLocationBuilder = apply { this.name = name }

    override fun geo(geo: GeographicLocation?): PodcastindexLocationBuilder = apply { this.geo = geo }

    override fun osm(osm: OpenStreetMapElement?): PodcastindexLocationBuilder = apply { this.osm = osm }

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

    override fun toString(): String = "FakePodcastindexLocationBuilder(name=$name, geo=$geo, osm=$osm)"
}
