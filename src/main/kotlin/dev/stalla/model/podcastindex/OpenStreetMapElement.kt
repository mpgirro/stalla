package dev.stalla.model.podcastindex

import dev.stalla.builder.OpenStreetMapElementBuilder
import dev.stalla.builder.validating.ValidatingOpenStreetMapElementBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.TypeFactory
import dev.stalla.parser.OpenStreetMapElementParser

/**
 * Represents an [OpenStreetMap element](https://wiki.openstreetmap.org/wiki/Elements).
 *
 * Direct instantiation in Java is discouraged. Use the [builder][OpenStreetMapElement.Factory.builder]
 * method to obtain a [OpenStreetMapElement] instance for expressive construction instead.
 *
 * Use the [of][OpenStreetMapElement.Factory.of] method to obtain an instance from a string pattern.
 *
 * @property type A one-character description of the type of OSM point. One of the supported [OpenStreetMapElementType]s.
 * @property id The ID of the OpenStreetMap element.
 * @property revision An optional revision ID - the "changeset" of the element.
 *
 * @see [wiki.openstreetmap.org/wiki/Elements](https://wiki.openstreetmap.org/wiki/Elements)
 * @since 1.1.0
 */
public data class OpenStreetMapElement(
    val type: OpenStreetMapElementType,
    val id: Long,
    val revision: Int?
) {

    override fun toString(): String = StringBuilder().apply {
        append(type.type)
        append(id)
        if (revision != null) append("#$revision")
    }.toString()

    /** Provides a builder and a factory for the [OpenStreetMapElement] class. */
    public companion object Factory : BuilderFactory<OpenStreetMapElement, OpenStreetMapElementBuilder>, TypeFactory<OpenStreetMapElement> {

        /** Returns a builder implementation for building [OpenStreetMapElement] model instances. */
        @JvmStatic
        override fun builder(): OpenStreetMapElementBuilder = ValidatingOpenStreetMapElementBuilder()

        @JvmStatic
        override fun of(rawValue: String?): OpenStreetMapElement? = rawValue?.let { value -> OpenStreetMapElementParser.parse(value) }
    }
}
