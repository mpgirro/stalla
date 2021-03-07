package dev.stalla.model.itunes

import dev.stalla.model.TypeFactory
import dev.stalla.util.equalsIgnoreCase

/**
 * Supported show types encountered within the `<itunes:type>` element
 * within an `<item>` element, modeled as a finite set enum class. See the
 * [reference docs](https://help.apple.com/itc/podcasts_connect/#/itcb54353390)
 * for more information.
 *
 * @property type The string representation of the Enum instance.
 *
 * @see ShowType.Factory Provides a factory method for type instances.
 * @since 1.0.0
 */
public enum class ShowType(public val type: String) {

    /** Type describing an episodic show. */
    EPISODIC("episodic"),

    /** Type describing a serial show. */
    SERIAL("serial");

    /** Gets an instance of [ShowType] from a raw value. */
    public companion object Factory : TypeFactory<ShowType> {

        @JvmStatic
        override fun of(rawValue: String?): ShowType? = rawValue?.let {
            values().find { t -> t.type.equalsIgnoreCase(it) }
        }
    }
}
