package dev.stalla.builder.fake

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.PodcastindexLocation

internal class FakePodcastindexLocationBuilder: FakeBuilder<PodcastindexLocation>(), PodcastindexLocationBuilder {

    var nameValue: String? = null
    var geoValue: GeoLocation? = null
    var osmValue: OpenStreetMapFeature? = null

    override fun name(name: String): PodcastindexLocationBuilder = apply { this.nameValue = name }

    override fun geo(geo: GeoLocation?): PodcastindexLocationBuilder = apply { this.geoValue = geo }

    override fun osm(osm: OpenStreetMapFeature?): PodcastindexLocationBuilder = apply { this.osmValue = osm }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakePodcastindexLocationBuilder

        if (nameValue != other.nameValue) return false
        if (geoValue != other.geoValue) return false
        if (osmValue != other.osmValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nameValue?.hashCode() ?: 0
        result = 31 * result + (geoValue?.hashCode() ?: 0)
        result = 31 * result + (osmValue?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "FakePodcastindexLocationBuilder(nameValue=$nameValue, geoValue=$geoValue, osmValue=$osmValue)"
    }
}
