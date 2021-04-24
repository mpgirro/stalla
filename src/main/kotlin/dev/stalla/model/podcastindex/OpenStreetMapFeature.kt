package dev.stalla.model.podcastindex

// What OpenStreetMap calls "OSM type and OSM id".
// See: https://github.com/Podcastindex-org/podcast-namespace/issues/138#issue-758103104

/**
 * @property osmType A one-character description of the type of OSM point. One of the supported [OsmType]s.
 * @property osmId The ID of the OpenStreetMap feature that is described.
 * @property osmRevision An optional revision ID for an OSM object, preceded by a hash..
 */
public data class OpenStreetMapFeature(
    val osmType: OsmType,
    val osmId: Int,
    val osmRevision: String?
) {
    // TODO
}
