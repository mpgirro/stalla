package dev.stalla.model.googleplay

/**
 * Enum model for the defined values encountered within the
 * `<googleplay:explicit>` element within a `<item>` element.
 *
 * @property type The string representation of the enum instance.
 */
public enum class ExplicitType(public val type: String) {

    /** Type describing a `yes` value. */
    YES("yes"),

    /** Type describing a `no` value. */
    NO("no"),

    /** Type describing a `clean` value. */
    CLEAN("clean");

    public companion object Factory {

        /**
         * Factory method for the instance of the Enum matching the [type] parameter.
         *
         * @param type The string representation of the Enum instance.
         * @return The Enum instance matching [type], or null if not matching instance exists.
         */
        public fun from(type: String?): ExplicitType? = type?.let {
            values().find { t -> t.type == it.toLowerCase() }
        }
    }
}
