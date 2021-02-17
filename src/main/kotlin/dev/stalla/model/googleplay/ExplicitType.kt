package dev.stalla.model.googleplay

import dev.stalla.model.TypeFactory

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

    public companion object Factory : TypeFactory<ExplicitType> {

        @JvmStatic
        override fun of(type: String?): ExplicitType? = type?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }
    }
}
