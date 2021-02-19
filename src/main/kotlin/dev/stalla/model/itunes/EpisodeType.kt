package dev.stalla.model.itunes

import dev.stalla.model.TypeFactory

/**
 * Supported episode types encountered within the `<itunes:episodeType>`
 * element within an `<item>` element, modeled as a finite set enum class.
 * See the [reference docs](https://help.apple.com/itc/podcasts_connect/#/itcb54353390)
 * for more information.
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
