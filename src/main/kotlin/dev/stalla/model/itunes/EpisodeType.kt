package dev.stalla.model.itunes

/**
 * Enum model for the defined values encountered within the
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

    public companion object Factory {

        /**
         * Factory method for the instance of the Enum matching the [type] parameter.
         *
         * @param type The string representation of the Enum instance.
         * @return The Enum instance matching [type], or null if not matching instance exists.
         */
        public fun from(type: String?): EpisodeType? = type?.let {
            values().find { value -> value.type == it.toLowerCase() }
        }
    }
}
