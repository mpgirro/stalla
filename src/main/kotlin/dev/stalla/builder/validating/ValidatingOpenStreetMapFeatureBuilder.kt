package dev.stalla.builder.validating

import dev.stalla.builder.OpenStreetMapFeatureBuilder
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.OsmType
import dev.stalla.util.InternalAPI
import java.math.BigInteger

@InternalAPI
internal class ValidatingOpenStreetMapFeatureBuilder : OpenStreetMapFeatureBuilder {

    private lateinit var typeValue: OsmType
    private lateinit var idValue: BigInteger
    private var revisionValue: BigInteger? = null

    override fun type(type: OsmType): OpenStreetMapFeatureBuilder = apply { this.typeValue = type }

    override fun id(id: BigInteger): OpenStreetMapFeatureBuilder = apply { this.idValue = id }

    override fun revision(revision: BigInteger?): OpenStreetMapFeatureBuilder = apply { this.revisionValue = revision }

    override val hasEnoughDataToBuild: Boolean
        get() = ::typeValue.isInitialized && ::idValue.isInitialized

    override fun build(): OpenStreetMapFeature? {
        if (!hasEnoughDataToBuild) return null

        return OpenStreetMapFeature(
            type = typeValue,
            id = idValue,
            revision = revisionValue
        )
    }

}
