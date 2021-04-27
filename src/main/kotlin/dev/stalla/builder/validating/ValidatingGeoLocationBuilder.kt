package dev.stalla.builder.validating

import dev.stalla.builder.GeoLocationBuilder
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.GeoLocation.Factory.CRS_PARAM
import dev.stalla.model.podcastindex.GeoLocation.Factory.UNCERTAINTY_PARAM
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingGeoLocationBuilder : GeoLocationBuilder {

    private var latitude: Double? = null
    private var longitude: Double? = null
    private var altitude: Double? = null
    private var crs: String? = null
    private var uncertainty: Double? = null
    private var parameters: MutableMap<String, String> = mutableMapOf()

    override fun latitude(latitude: Double): GeoLocationBuilder = apply { this.latitude = latitude }

    override fun longitude(longitude: Double): GeoLocationBuilder = apply { this.longitude = longitude }

    override fun altitude(altitude: Double?): GeoLocationBuilder = apply { this.altitude = altitude }

    override fun crs(crs: String?): GeoLocationBuilder = apply { this.crs = crs }

    override fun uncertainty(uncertainty: Double?): GeoLocationBuilder = apply { this.uncertainty = uncertainty }

    override fun addParameter(key: String, value: String): GeoLocationBuilder = apply {
        if (CRS_PARAM.equals(key, ignoreCase = true)) {
            crs(value)
            return@apply
        }
        if (UNCERTAINTY_PARAM.equals(key, ignoreCase = true)) {
            try {
                uncertainty(value.toDouble())
                return@apply
            } catch (e: NumberFormatException) {
                // if it can't be parsed, then treat it as an ordinary parameter
            }
        }
        parameters[key] = value
    }

    override fun removeParameter(key: String): GeoLocationBuilder = apply {
        if (CRS_PARAM.equals(key, ignoreCase = true)) {
            crs(null)
        }
        if (UNCERTAINTY_PARAM.equals(key, ignoreCase = true)) {
            uncertainty(null)
        }
        parameters.remove(key)
    }

    override fun removeParameter(parameter: GeoLocation.Parameter): GeoLocationBuilder =
        removeParameter(parameter.key)

    override fun hasLatitude(): Boolean = latitude != null

    override fun hasLongitude(): Boolean = longitude != null

    override fun hasAltitude(): Boolean = altitude != null

    override val hasEnoughDataToBuild: Boolean
        get() = latitude != null && longitude != null

    override fun build(): GeoLocation? {
        if (!hasEnoughDataToBuild) return null

        return GeoLocation(
            latitude = latitude ?: return null,
            longitude = longitude ?: return null,
            altitude = altitude,
            crs = crs,
            uncertainty = uncertainty,
            parameters = parameters // this secondary constructor will apply .asUnmodifiable()
        )
    }

}
