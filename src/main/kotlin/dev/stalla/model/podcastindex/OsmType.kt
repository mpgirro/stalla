package dev.stalla.model.podcastindex

import dev.stalla.model.TypeFactory

/**
 * TODO.
 *
 * @property type TODO.
 */
public enum class OsmType(public val type: String) {
    Node("N"),
    Way("W"),
    Relation("R");

    /**
     * TODO.
     */
    public companion object Factory : TypeFactory<OsmType> {
        @JvmStatic
        override fun of(rawValue: String?): OsmType? = rawValue?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }
    }
}
