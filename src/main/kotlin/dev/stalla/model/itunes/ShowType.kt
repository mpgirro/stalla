package dev.stalla.model.itunes

import dev.stalla.model.TypeFactory

/**
 * Enum model for the defined values encountered within the
 * `<itunes:type>` element within a `<channel>` element.
 *
 * @property type The string representation of the Enum instance.
 */
public enum class ShowType(public val type: String) {

    /** Type describing an episodic show. */
    EPISODIC("episodic"),

    /** Type describing a serial show. */
    SERIAL("serial");

    public companion object Factory : TypeFactory<ShowType> {

        override fun of(type: String?): ShowType? = type?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }
    }
}
