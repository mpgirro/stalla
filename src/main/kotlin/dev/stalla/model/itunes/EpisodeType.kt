package dev.stalla.model.itunes

import dev.stalla.model.TypeFactory
import dev.stalla.util.equalsIgnoreCase

/**
 * Supported episode types encountered within the `<itunes:episodeType>`
 * element within an `<item>` element, modeled as a finite set enum class.
 * See the [reference docs](https://help.apple.com/itc/podcasts_connect/#/itcb54353390)
 * for more information.
 *
 * @property type The string representation of the enum instance.
 *
 * @see EpisodeType.Factory Provides a factory method for type instances.
 * @since 1.0.0
 */
public enum class EpisodeType(public val type: String) {

    /** Type describing a bonus episode. */
    BONUS("bonus"),

    /** Type describing a full episode. */
    FULL("full"),

    /** Type describing a trailer episode. */
    TRAILER("trailer");

    /** Gets an instance of [EpisodeType] from a raw value. */
    public companion object Factory : TypeFactory<EpisodeType> {

        @JvmStatic
        override fun of(rawValue: String?): EpisodeType? = rawValue?.let {
            values().find { t -> t.type.equalsIgnoreCase(it) }
        }
    }
}
