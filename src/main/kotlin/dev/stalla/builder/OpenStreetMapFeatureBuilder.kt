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

    /** Set the type value. */
    public fun type(type: OpenStreetMapElementType): OpenStreetMapFeatureBuilder

    /** Set the id value. */
    public fun id(id: BigInteger): OpenStreetMapFeatureBuilder

    /** Set the id value. */
    public fun id(id: Int): OpenStreetMapFeatureBuilder = apply { id(id.toBigInteger()) }

    /** Set the id value. */
    public fun id(id: Long): OpenStreetMapFeatureBuilder = apply { id(id.toBigInteger()) }

    /** Set the revision value. */
    public fun revision(revision: BigInteger?): OpenStreetMapFeatureBuilder

    /** Set the revision value. */
    public fun revision(revision: Int?): OpenStreetMapFeatureBuilder = apply { revision(revision.asBigIntegerOrNull()) }

    /** Set the revision value. */
    public fun revision(revision: Long?): OpenStreetMapFeatureBuilder = apply { revision(revision.asBigIntegerOrNull()) }

    override fun applyFrom(prototype: OpenStreetMapFeature?): OpenStreetMapFeatureBuilder =
        whenNotNull(prototype) { feature ->
            type(feature.type)
            id(feature.id)
            revision(feature.revision)
        }

}
