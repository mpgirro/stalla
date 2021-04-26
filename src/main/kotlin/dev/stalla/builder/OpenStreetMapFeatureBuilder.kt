package dev.stalla.builder

import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.OpenStreetMapElementType
import dev.stalla.util.asBigIntegerOrNull
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

    public fun id(id: Int): OpenStreetMapFeatureBuilder = apply { id(id.toBigInteger()) }

    public fun id(id: Long): OpenStreetMapFeatureBuilder = apply { id(id.toBigInteger()) }

    public fun revision(revision: BigInteger?): OpenStreetMapFeatureBuilder

    public fun revision(revision: Int?): OpenStreetMapFeatureBuilder = apply { revision(revision.asBigIntegerOrNull()) }

    public fun revision(revision: Long?): OpenStreetMapFeatureBuilder = apply { revision(revision.asBigIntegerOrNull()) }

    override fun applyFrom(prototype: OpenStreetMapFeature?): OpenStreetMapFeatureBuilder =
        whenNotNull(prototype) { feature ->
            type(feature.type)
            id(feature.id)
            revision(feature.revision)
        }

}
