package dev.stalla.model.podcastindex

/**
 * @property name Human-readable place name.
 * @property geo TODO.
 * @property osm TODO.
 */
public data class PodcastindexLocation(
    val name: String,
    val geo: GeoLocation?,
    val osm: OpenStreetMapFeature?
) {
    // TODO
}
