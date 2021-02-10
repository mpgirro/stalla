package dev.stalla.model.itunes

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

    public companion object Factory {

        /**
         * Factory method for the instance of the Enum matching the [type] parameter.
         *
         * @param type The string representation of the Enum instance.
         * @return The Enum instance matching [type], or null if not matching instance exists.
         */
        public fun from(type: String?): ShowType? = type?.let {
            values().find { t -> t.type == it.toLowerCase() }
        }
    }
}
