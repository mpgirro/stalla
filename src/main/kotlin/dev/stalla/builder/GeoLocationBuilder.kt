package dev.stalla.builder

import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [GeoLocation] instances.
 *
 * @since 1.1.0
 */
public interface GeoLocationBuilder : Builder<GeoLocation> {

    /** Set the latitude value. */
    public fun latitude(latitude: Double): GeoLocationBuilder

    /** Set the longitude value. */
    public fun longitude(longitude: Double): GeoLocationBuilder

    /** Set the altitude value. */
    public fun altitude(altitude: Double?): GeoLocationBuilder

    /** Set the crs value. */
    public fun crs(crs: String?): GeoLocationBuilder

    /** Set the uncertainty value. */
    public fun uncertainty(uncertainty: Double?): GeoLocationBuilder

    /** Adds a [GeoLocation.Parameter] based on [key] and [value] to the list of parameters. */
    public fun addParameter(key: String, value: String): GeoLocationBuilder

    /** Adds a [GeoLocation.Parameter] to the list of parameters. */
    public fun addParameter(parameter: GeoLocation.Parameter): GeoLocationBuilder =
        apply { addParameter(parameter.key, parameter.value) }

    /** Adds all [GeoLocation.Parameter] to the list of parameters. */
    public fun addAllParameters(parameters: List<GeoLocation.Parameter>): GeoLocationBuilder =
        apply { parameters.forEach(::addParameter) }

    /** Removes the parameter with [key] from the list of parameters. */
    public fun removeParameter(key: String): GeoLocationBuilder

    /** Removes [parameter] from the list of parameters. */
    public fun removeParameter(parameter: GeoLocation.Parameter): GeoLocationBuilder

    /** Returns `true` if the coordA property is set. */
    public fun hasLatitude(): Boolean

    /** Returns `true` if the coordB property is set. */
    public fun hasLongitude(): Boolean

    /** Returns `true` if the coordC property is set. */
    public fun hasAltitude(): Boolean

    override fun applyFrom(prototype: GeoLocation?): GeoLocationBuilder =
        whenNotNull(prototype) { location ->
            latitude(location.latitude)
            longitude(location.longitude)
            altitude(location.altitude)
            crs(location.crs)
            uncertainty(location.uncertainty)
            addAllParameters(location.parameters)
        }
}
