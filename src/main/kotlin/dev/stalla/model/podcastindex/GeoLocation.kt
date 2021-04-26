package dev.stalla.model.podcastindex

import dev.stalla.builder.GeoLocationBuilder
import dev.stalla.builder.validating.ValidatingGeoLocationBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.TypeFactory
import dev.stalla.model.podcastindex.GeoLocation.Factory.CRS_WGS84
import dev.stalla.parser.GeoUriParser
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable
import dev.stalla.util.containsMediaTypeSeparatorSymbol
import java.util.Locale
import kotlin.contracts.contract
import kotlin.math.absoluteValue

/**
 * TODO.
 *
 * @property coordA Latitude.
 * @property coordB Longitude.
 * @property coordC Altitude.
 * @property crs The coordinate reference system - defaults to ([CRS_WGS84]) if not set.
 * @property uncertainty TODO.
 * @property parameters TODO.
 *
 * @see [RFC 5870](https://tools.ietf.org/html/rfc5870)
 * @since 1.1.0
 */
public class GeoLocation public constructor(
    public val coordA: Double,
    public val coordB: Double,
    public val coordC: Double? = null,
    public val crs: String? = null,
    public val uncertainty: Double? = null,
    public val parameters: List<Parameter> = emptyList()
) {

    public constructor(
        coordA: Double,
        coordB: Double,
        coordC: Double?,
        crs: String?,
        uncertainty: Double?,
        parameters: Map<String, String>
    ) : this(
        coordA = coordA,
        coordB = coordB,
        coordC = coordC,
        crs = crs,
        uncertainty = uncertainty,
        parameters = parameters.map { Parameter(it.key, it.value) }.asUnmodifiable()
    )

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

        return GeoLocation(coordA, coordB, coordC, crs, uncertainty, parameters + Parameter(key, value))
    }

    /** Creates a copy of `this` type without any parameters.*/
    public fun withoutParameters(): GeoLocation = GeoLocation(coordA, coordB, coordC)

    /** Checks if `this` type matches a [pattern] type taking parameters into account. */
    public fun match(pattern: GeoLocation?): Boolean {
        contract {
            returns(true) implies (pattern != null)
        }

        if (pattern == null) return false
        if (this == pattern) return true
        if (coordA.absoluteValue == 90.0 && matchCrs(crs, pattern.crs)) {
            // Special "poles" rule for WGS-84 applies - longitude is to be ignored
            return coordA == pattern.coordA &&
                coordC == pattern.coordC &&
                uncertainty == pattern.uncertainty &&
                match(parameters, pattern.parameters)
        }

        return coordA == pattern.coordA &&
            coordB == pattern.coordB &&
            coordC == pattern.coordC &&
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

    override fun toString(): String = StringBuilder().apply {
        append("geo:$coordA,$coordB")
        if (coordC != null) append(",$coordC")
        if (crs != null) append(";$PARAM_CRS=$crs")
        if (uncertainty != null) append(";$PARAM_UNCERTAINTY=$uncertainty")
        for ((key, value) in parameters) append(";$key=$value")
    }.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GeoLocation

        if (coordA != other.coordA) return false
        if (coordB != other.coordB) return false
        if (coordC != other.coordC) return false
        if (crs != other.crs) return false
        if (uncertainty != other.uncertainty) return false
        if (parameters != other.parameters) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coordA.hashCode()
        result = 31 * result + coordB.hashCode()
        result = 31 * result + (coordC?.hashCode() ?: 0)
        result = 31 * result + (crs?.hashCode() ?: 0)
        result = 31 * result + (uncertainty?.hashCode() ?: 0)
        result = 31 * result + parameters.hashCode()
        return result
    }

    /**
     * TODO.
     */
    public companion object Factory : BuilderFactory<GeoLocation, GeoLocationBuilder>, TypeFactory<GeoLocation> {

        /** The World Geodetic System 1984 coordinate reference system used by GPS. */
        public const val CRS_WGS84: String = "WGS84"

        internal const val PARAM_CRS = "crs"
        internal const val PARAM_UNCERTAINTY = "u"

        /** Returns a builder implementation for building [GeoLocation] model instances. */
        @JvmStatic
        override fun builder(): GeoLocationBuilder = ValidatingGeoLocationBuilder()

        @JvmStatic
        override fun of(rawValue: String?): GeoLocation? = rawValue?.let { value -> GeoUriParser.parse(value) }

    }
}
