package dev.stalla.model.podcastindex

import dev.stalla.model.TypeFactory
import dev.stalla.parser.OsmFeatureParser
import java.math.BigInteger

// What OpenStreetMap calls "OSM type and OSM id".
// See: https://github.com/Podcastindex-org/podcast-namespace/issues/138#issue-758103104

/**
 * @property type A one-character description of the type of OSM point. One of the supported [OsmType]s.
 * @property id The ID of the OpenStreetMap feature that is described.
 * @property revision An optional revision ID for an OSM object, preceded by a hash.
 */
public data class OpenStreetMapFeature(
    val type: OsmType,
    val id: BigInteger,
    val revision: String?
) {

    override fun toString(): String = StringBuilder().apply {
        append(type.type)
        append(id)
        if (revision != null) append("#$revision")
    }.toString()

    /**
     * TODO.
     */
    public companion object Factory : TypeFactory<OpenStreetMapFeature> {
        @JvmStatic
        override fun of(rawValue: String?): OpenStreetMapFeature? = rawValue?.let { value -> OsmFeatureParser.parse(value) }
    }
}
