package dev.stalla.model.googleplay

import dev.stalla.model.TypeFactory

/**
 * Supported explicit types encountered within the `<googleplay:explicit>` element
 * within an `<item>` element, modeled as a finite set enum class. See the
 * [reference docs](https://support.google.com/podcast-publishers/answer/9889544) and
 * the [XML schema definition](https://www.google.com/schemas/play-podcasts/1.0/play-podcasts.xsd)
 * for more information.
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
        override fun of(rawValue: String?): ExplicitType? = rawValue?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }
    }
}
