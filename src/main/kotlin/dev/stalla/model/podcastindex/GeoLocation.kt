package dev.stalla.model.podcastindex

import dev.stalla.builder.GeoLocationBuilder
import dev.stalla.builder.validating.ValidatingGeoLocationBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.TypeFactory
import dev.stalla.model.podcastindex.GeoLocation.Factory.CRS_WGS84
import dev.stalla.parser.GeoUriParser
import dev.stalla.util.asUnmodifiable
import dev.stalla.util.containsMediaTypeSeparatorSymbol
import java.util.Locale
import kotlin.contracts.contract
import kotlin.math.absoluteValue

/**
 * Represents a Geographic Location ('geo' URI) as defined by [RFC 5870](https://tools.ietf.org/html/rfc5870).
 *
 * Note that this class only ensures syntactically valid geo URI constructs,
 * but does not ensure that the coordinate values are valid within the coordinate
 * reference system ([crs]), besides the comparison rules that apply for WGS-84.
 *
 * @property latitude The latitude value.
 * @property longitude The longitude value.
 * @property altitude The altitude value.
 * @property crs The coordinate reference system - defaults to ([CRS_WGS84]) if not set.
 * @property uncertainty The amount of uncertainty in the location as a value in meters.
 * @property parameters TODO.
 *
 * @see [RFC 5870](https://tools.ietf.org/html/rfc5870)
 * @since 1.1.0
 */
public class GeoLocation public constructor(
    public val latitude: Double,
    public val longitude: Double,
    public val altitude: Double? = null,
    public val crs: String? = null,
    public val uncertainty: Double? = null,
    public val parameters: List<Parameter> = emptyList()
) {

    public constructor(
        latitude: Double,
        longitude: Double,
        altitude: Double?,
        crs: String?,
        uncertainty: Double?,
        parameters: Map<String, String>
    ) : this(
        latitude = latitude,
        longitude = longitude,
        altitude = altitude,
        crs = crs,
        uncertainty = uncertainty,
        parameters = parameters.map { Parameter(it.key, it.value) }.asUnmodifiable()
    )

    /**
     * Represents a single value parameter.
     *
     * @property key The key of parameter.
     * @property value The value of parameter.
     */
    public data class Parameter(val key: String, val value: String) {
        override fun equals(other: Any?): Boolean {
            return other is Parameter &&
                other.key.equals(key, ignoreCase = true) &&
                other.value == value
        }

        override fun hashCode(): Int {
            var result = key.toLowerCase(Locale.ROOT).hashCode()
            result += 31 * result + value.toLowerCase(Locale.ROOT).hashCode()
            return result
        }
    }

    /**
     * The first value for the parameter with [key] comparing
     * case-insensitively or `null` if no such parameters found.
     */
    public fun parameter(key: String): String? =
        parameters.firstOrNull { it.key.equals(key, ignoreCase = true) }?.value

    /** Creates a copy of `this` type with an added parameter of [key] and [value]. */
    public fun withParameter(key: String, value: String): GeoLocation {
        if (key.isBlank() || key.containsMediaTypeSeparatorSymbol()) return this
        if (value.isBlank()) return this
        if (hasParameter(key, value)) return this

        return GeoLocation(latitude, longitude, altitude, crs, uncertainty, parameters + Parameter(key, value))
    }

    /** Creates a copy of `this` type without any parameters.*/
    public fun withoutParameters(): GeoLocation = GeoLocation(latitude, longitude, altitude)

    /** Checks if `this` type matches a [pattern] type taking parameters into account. */
    public fun match(pattern: GeoLocation?): Boolean {
        contract {
            returns(true) implies (pattern != null)
        }

        if (pattern == null) return false
        if (this == pattern) return true

        if (crs.isWGS84() && pattern.crs.isWGS84()) {
            if (latitude.isPoleWGS84() || pattern.latitude.isPoleWGS84()) {
                // Special "poles" rule for WGS-84 applies - longitude is to be ignored
                return latitude == pattern.latitude &&
                    altitude == pattern.altitude &&
                    uncertainty == pattern.uncertainty &&
                    match(parameters, pattern.parameters)
            }

            if (longitude.isDateLineWGS84() && pattern.longitude.isDateLineWGS84()) {
                // Special "date line" rule for WGS-84 applies - longitude 180 degrees == -180 degrees
                return latitude == pattern.latitude &&
                    altitude == pattern.altitude &&
                    uncertainty == pattern.uncertainty &&
                    match(parameters, pattern.parameters)
            }
        }

        return latitude == pattern.latitude &&
            longitude == pattern.longitude &&
            altitude == pattern.altitude &&
            crs.equals(pattern.crs, ignoreCase = true) &&
            uncertainty == pattern.uncertainty &&
            match(parameters, pattern.parameters)
    }

    /** Checks if `this` type matches a [pattern] type taking parameters into account. */
    public fun match(pattern: String): Boolean = match(of(pattern))

    private fun match(parameters1: List<Parameter>, parameters2: List<Parameter>): Boolean {
        for (param1 in parameters1) {
            val value2 = parameter(param1.key, parameters2)
            val matches = param1.match(param1.key, value2)

            if (!matches) return false
        }
        return true
    }

    private fun Parameter.match(key: String, value: String?): Boolean =
        if (value == null) false else this == Parameter(key, value)

    private fun matchCrs(crs1: String?, crs2: String?): Boolean {
        return (crs1 == null || crs1.equals(CRS_WGS84, ignoreCase = true))
            && (crs2 == null || crs2.equals(CRS_WGS84, ignoreCase = true))
    }

    private fun parameter(key: String, elements: List<Parameter>): String? =
        elements.firstOrNull { it.key.equals(key, ignoreCase = true) }?.value

    private fun hasParameter(key: String, value: String): Boolean = when (parameters.size) {
        0 -> false
        1 -> parameters[0].let { param ->
            param.key.equals(key, ignoreCase = true) && param.value.equals(value, ignoreCase = true)
        }
        else -> parameters.any { param ->
            param.key.equals(key, ignoreCase = true) && param.value.equals(value, ignoreCase = true)
        }
    }

    private fun String?.isWGS84(): Boolean = this == null || this.equals(CRS_WGS84, ignoreCase = true)

    private fun Double.isPoleWGS84(): Boolean = this.absoluteValue == MAX_LATITUDE

    private fun Double.isDateLineWGS84(): Boolean = this.absoluteValue == MAX_LONGITUDE

    private fun Double.equalDateLineWGS84(other: Double): Boolean {
        return if (this.isDateLineWGS84() && other.isDateLineWGS84()) {
            true
        } else {
            this == other
        }
    }

    override fun toString(): String = StringBuilder().apply {
        append("geo:$latitude,$longitude")
        if (altitude != null) append(",$altitude")
        if (crs != null) append(";$CRS_PARAM=$crs")
        if (uncertainty != null) append(";$UNCERTAINTY_PARAM=$uncertainty")
        for ((key, value) in parameters) append(";$key=$value")
    }.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GeoLocation

        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (altitude != other.altitude) return false
        if (crs != other.crs) return false
        if (uncertainty != other.uncertainty) return false
        if (parameters != other.parameters) return false

        return true
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        result = 31 * result + (altitude?.hashCode() ?: 0)
        result = 31 * result + (crs?.hashCode() ?: 0)
        result = 31 * result + (uncertainty?.hashCode() ?: 0)
        result = 31 * result + parameters.hashCode()
        return result
    }

    /** Provides a builder and a factory for the [GeoLocation] class. */
    public companion object Factory : BuilderFactory<GeoLocation, GeoLocationBuilder>, TypeFactory<GeoLocation> {

        /** The World Geodetic System 1984 coordinate reference system used by GPS. */
        public const val CRS_WGS84: String = "WGS84"

        internal const val CRS_PARAM = "crs"
        internal const val UNCERTAINTY_PARAM = "u"
        internal const val MAX_LATITUDE = 90.0
        internal const val MAX_LONGITUDE = 180.0

        /** Returns a builder implementation for building [GeoLocation] model instances. */
        @JvmStatic
        override fun builder(): GeoLocationBuilder = ValidatingGeoLocationBuilder()

        @JvmStatic
        override fun of(rawValue: String?): GeoLocation? = rawValue?.let { value -> GeoUriParser.parse(value) }

    }
}
