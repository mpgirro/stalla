package dev.stalla.builder

import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.OpenStreetMapElementType
import dev.stalla.util.whenNotNull
import java.math.BigInteger

/**
 * Builder for constructing [OpenStreetMapFeature] instances.
 *
 * @since 1.1.0
 */
public interface OpenStreetMapFeatureBuilder : Builder<OpenStreetMapFeature> {

    public fun type(type: OpenStreetMapElementType): OpenStreetMapFeatureBuilder

    public fun id(id: BigInteger): OpenStreetMapFeatureBuilder

    public fun revision(revision: BigInteger?): OpenStreetMapFeatureBuilder

    override fun applyFrom(prototype: OpenStreetMapFeature?): OpenStreetMapFeatureBuilder =
        whenNotNull(prototype) { feature ->
            type(feature.type)
            id(feature.id)
            revision(feature.revision)
        }
}
