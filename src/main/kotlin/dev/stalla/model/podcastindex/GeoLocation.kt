package dev.stalla.model.podcastindex

import dev.stalla.model.TypeFactory
import dev.stalla.parser.GeoUriParser
import java.util.Locale

// TODO https://en.wikipedia.org/wiki/Geo_URI_scheme
// RFC 5870: https://tools.ietf.org/html/rfc5870

/**
 * TODO.
 *
 * @property coordA TODO.
 * @property coordB TODO.
 * @property coordC TODO.
 * @property parameters TODO.
 */
public class GeoLocation internal constructor(
    public val coordA: Double,
    public val coordB: Double,
    public val coordC: Double? = null,
    public val crs: String? = null,
    public val uncertainty: Double? = null,
    public val parameters: Map<String, String> = emptyMap()
) {

    /**
     * TODO.
     *
     * @property key TODO.
     * @property value TODO.
     */
    public data class Parameter(val key: String, val value: String) {
        override fun equals(other: Any?): Boolean {
            return other is Parameter &&
                other.key.equals(key, ignoreCase = true) &&
                other.value.equals(value, ignoreCase = true)
        }

        override fun hashCode(): Int {
            var result = key.toLowerCase(Locale.ROOT).hashCode()
            result += 31 * result + value.toLowerCase(Locale.ROOT).hashCode()
            return result
        }
    }

    // TODO

    /**
     * TODO extend TypeFactory.
     */
    public companion object Factory : TypeFactory<GeoLocation> {
        @JvmStatic
        override fun of(rawValue: String?): GeoLocation? = rawValue?.let { value -> GeoUriParser.parse(value) }
    }
}
