package dev.stalla.model.podcastindex

import dev.stalla.builder.OpenStreetMapFeatureBuilder
import dev.stalla.builder.validating.ValidatingOpenStreetMapFeatureBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.TypeFactory
import dev.stalla.parser.OsmFeatureParser
import dev.stalla.util.asBigIntegerOrNull
import java.math.BigInteger

/**
 * TODO.
 *
 * @property type A one-character description of the type of OSM point. One of the supported [OpenStreetMapElementType]s.
 * @property id The ID of the OpenStreetMap feature that is described.
 * @property revision An optional revision ID for an OSM object, preceded by a hash.
 *
 * @since 1.1.0
 */
public data class OpenStreetMapFeature(
    val type: OpenStreetMapElementType,
    val id: BigInteger,
    val revision: BigInteger?
) {

    @Suppress("Unused")
    public constructor(
        type: OpenStreetMapElementType,
        id: Int,
        revision: Int?
    ): this(type, id.toBigInteger(), revision.asBigIntegerOrNull())

    @Suppress("Unused")
    public constructor(
        type: OpenStreetMapElementType,
        id: Int,
        revision: Long?
    ): this(type, id.toBigInteger(), revision.asBigIntegerOrNull())

    @Suppress("Unused")
    public constructor(
        type: OpenStreetMapElementType,
        id: Long,
        revision: Long?
    ): this(type, id.toBigInteger(), revision.asBigIntegerOrNull())

    @Suppress("Unused")
    public constructor(
        type: OpenStreetMapElementType,
        id: Long,
        revision: Int?
    ): this(type, id.toBigInteger(), revision.asBigIntegerOrNull())

    override fun toString(): String = StringBuilder().apply {
        append(type.type)
        append(id)
        if (revision != null) append("#$revision")
    }.toString()

    /**
     * TODO.
     */
    public companion object Factory : BuilderFactory<OpenStreetMapFeature, OpenStreetMapFeatureBuilder>, TypeFactory<OpenStreetMapFeature> {

        /** Returns a builder implementation for building [OpenStreetMapFeature] model instances. */
        @JvmStatic
        override fun builder(): OpenStreetMapFeatureBuilder = ValidatingOpenStreetMapFeatureBuilder()

        @JvmStatic
        override fun of(rawValue: String?): OpenStreetMapFeature? = rawValue?.let { value -> OsmFeatureParser.parse(value) }
    }
}
