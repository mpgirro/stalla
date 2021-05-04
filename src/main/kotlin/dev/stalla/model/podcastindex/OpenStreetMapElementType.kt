package dev.stalla.model.podcastindex

import dev.stalla.model.TypeFactory

/**
 * Supported element types of [OpenStreeMap elements](https://wiki.openstreetmap.org/wiki/Elements).
 *
 * Use the [of][OpenStreetMapElementType.Factory.of] method to obtain an instance from a string pattern.
 *
 * @property type The string representation of the enum instance.
 *
 * @see OpenStreetMapElementType.Factory Provides a factory method for type instances.
 * @see [wiki.openstreetmap.org/wiki/Elements](https://wiki.openstreetmap.org/wiki/Elements)
 * @since 1.1.0
 */
public enum class OpenStreetMapElementType(public val type: String) {

    /** Type describing an OpenStreetMap node element. */
    Node("N"),

    /** Type describing an OpenStreetMap way element. */
    Way("W"),

    /** Type describing an OpenStreetMap relation element. */
    Relation("R");

    /** Gets an instance of [OpenStreetMapElementType] from a raw value. */
    public companion object Factory : TypeFactory<OpenStreetMapElementType> {

        @JvmStatic
        override fun of(rawValue: String?): OpenStreetMapElementType? = rawValue?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }

        /**
         * Factory method that returns the instance matching the [rawValue] parameter, if any.
         *
         * @param rawValue The case insensitive char representation of the instance.
         * @return The instance matching [rawValue], or `null` if no matching instance exists.
         */
        @JvmStatic
        public fun of(rawValue: Char?): OpenStreetMapElementType? = of(rawValue?.toString())
    }
}
