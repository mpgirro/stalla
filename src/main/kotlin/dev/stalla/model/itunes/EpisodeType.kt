package dev.stalla.model.itunes

import dev.stalla.model.TypeFactory

/**
 * Enum model for the finite value set encountered within the
 * `<itunes:episodeType>` element within a `<item>` element.
 *
 * @property type The string representation of the enum instance.
 */
public enum class EpisodeType(public val type: String) {

    /** Type describing a bonus episode. */
    BONUS("bonus"),

    /** Type describing a full episode. */
    FULL("full"),

    /** Type describing a trailer episode. */
    TRAILER("trailer");

    public companion object Factory : TypeFactory<EpisodeType> {

        @JvmStatic
        override fun of(rawValue: String?): EpisodeType? = rawValue?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }
    }
}
