package dev.stalla.builder

import dev.stalla.model.podcastindex.OpenStreetMapElement
import dev.stalla.model.podcastindex.OpenStreetMapElementType
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [OpenStreetMapElement] instances.
 *
 * @since 1.1.0
 */
public interface OpenStreetMapElementBuilder : Builder<OpenStreetMapElement> {

    /** Set the type value. */
    public fun type(type: OpenStreetMapElementType): OpenStreetMapElementBuilder

    /** Set the id value. */
    public fun id(id: Long): OpenStreetMapElementBuilder = apply { id(id) }

    /** Set the revision value. */
    public fun revision(revision: Int?): OpenStreetMapElementBuilder = apply { revision(revision) }

    override fun applyFrom(prototype: OpenStreetMapElement?): OpenStreetMapElementBuilder =
        whenNotNull(prototype) { feature ->
            type(feature.type)
            id(feature.id)
            revision(feature.revision)
        }
}
