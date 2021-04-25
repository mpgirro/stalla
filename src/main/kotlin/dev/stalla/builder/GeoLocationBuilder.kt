package dev.stalla.builder

import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [GeoLocation] instances.
 *
 * @since 1.1.0
 */
public interface GeoLocationBuilder : Builder<GeoLocation> {

    public fun coordA(coordA: Double): GeoLocationBuilder

    public fun coordB(coordB: Double): GeoLocationBuilder

    public fun coordC(coordC: Double?): GeoLocationBuilder

    public fun crs(crs: String?): GeoLocationBuilder

    public fun uncertainty(uncertainty: Double?): GeoLocationBuilder

    public fun addParameter(name: String, value: String): GeoLocationBuilder

    public fun addParameter(parameter: GeoLocation.Parameter): GeoLocationBuilder =
        apply { addParameter(parameter.key, parameter.value) }

    public fun addAllParameters(parameters: List<GeoLocation.Parameter>): GeoLocationBuilder =
        apply { parameters.forEach(::addParameter) }

    public fun removeParameter(name: String): GeoLocationBuilder

    public fun removeParameter(parameter: GeoLocation.Parameter): GeoLocationBuilder

    public fun hasCoordA(): Boolean

    public fun hasCoordB(): Boolean

    public fun hasCoordC(): Boolean

    override fun applyFrom(prototype: GeoLocation?): GeoLocationBuilder =
        whenNotNull(prototype) { location ->
            coordA(location.coordA)
            coordB(location.coordB)
            coordC(location.coordC)
            crs(location.crs)
            uncertainty(location.uncertainty)
            addAllParameters(location.parameters)
        }
}
