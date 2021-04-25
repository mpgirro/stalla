package dev.stalla.builder.validating

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingPodcastindexLocationBuilder : PodcastindexLocationBuilder {

    private lateinit var nameValue: String
    private var geo: GeoLocation? = null
    private var osm: OpenStreetMapFeature? = null

    override fun name(name: String): PodcastindexLocationBuilder = apply { this.nameValue = name }

    override fun geo(geo: GeoLocation?): PodcastindexLocationBuilder = apply { this.geo = geo }

    override fun osm(osm: OpenStreetMapFeature?): PodcastindexLocationBuilder = apply { this.osm = osm }

    override val hasEnoughDataToBuild: Boolean
        get() = ::nameValue.isInitialized

    override fun build(): PodcastindexLocation? {
        if (!hasEnoughDataToBuild) return null

        return PodcastindexLocation(
            name = nameValue,
            geo = geo,
            osm = osm
        )
    }
}
