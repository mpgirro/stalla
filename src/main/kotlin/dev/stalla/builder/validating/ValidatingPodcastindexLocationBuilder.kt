package dev.stalla.builder.validating

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingPodcastindexLocationBuilder : PodcastindexLocationBuilder {

    private lateinit var nameValue: String
    private var geoValue: GeoLocation? = null
    private var osmValue: OpenStreetMapFeature? = null

    override fun name(name: String): PodcastindexLocationBuilder = apply { this.nameValue = name }

    override fun geo(geo: GeoLocation?): PodcastindexLocationBuilder = apply { this.geoValue = geo }

    override fun osm(osm: OpenStreetMapFeature?): PodcastindexLocationBuilder = apply { this.osmValue = osm }

    override val hasEnoughDataToBuild: Boolean
        get() = ::nameValue.isInitialized

    override fun build(): PodcastindexLocation? {
        if (!hasEnoughDataToBuild) return null

        return PodcastindexLocation(
            name = nameValue,
            geo = geoValue,
            osm = osmValue
        )
    }
}
