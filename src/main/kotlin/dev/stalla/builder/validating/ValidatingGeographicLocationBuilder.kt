package dev.stalla.builder.validating

import dev.stalla.builder.GeographicLocationBuilder
import dev.stalla.model.podcastindex.GeographicLocation
import dev.stalla.model.podcastindex.GeographicLocation.Factory.CRS_PARAM
import dev.stalla.model.podcastindex.GeographicLocation.Factory.UNCERTAINTY_PARAM
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingGeographicLocationBuilder : GeographicLocationBuilder {

    private var latitude: Double? = null
    private var longitude: Double? = null
    private var altitude: Double? = null
    private var crs: String? = null
    private var uncertainty: Double? = null
    private var parameters: MutableMap<String, String> = mutableMapOf()

    override fun latitude(latitude: Double): GeographicLocationBuilder = apply { this.latitude = latitude }

    override fun longitude(longitude: Double): GeographicLocationBuilder = apply { this.longitude = longitude }

    override fun altitude(altitude: Double?): GeographicLocationBuilder = apply { this.altitude = altitude }

    override fun crs(crs: String?): GeographicLocationBuilder = apply { this.crs = crs }

    override fun uncertainty(uncertainty: Double?): GeographicLocationBuilder = apply { this.uncertainty = uncertainty }

    override fun addParameter(key: String, value: String): GeographicLocationBuilder = apply {
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

    override fun removeParameter(key: String): GeographicLocationBuilder = apply {
        if (CRS_PARAM.equals(key, ignoreCase = true)) crs(null)
        if (UNCERTAINTY_PARAM.equals(key, ignoreCase = true)) uncertainty(null)
        parameters.remove(key)
    }

    override fun removeParameter(parameter: GeographicLocation.Parameter): GeographicLocationBuilder =
        removeParameter(parameter.key)

    override fun hasLatitude(): Boolean = latitude != null

    override fun hasLongitude(): Boolean = longitude != null

    override fun hasAltitude(): Boolean = altitude != null

    override val hasEnoughDataToBuild: Boolean
        get() = latitude != null && longitude != null

    override fun build(): GeographicLocation? {
        if (!hasEnoughDataToBuild) return null

        return GeographicLocation(
            latitude = latitude ?: return null,
            longitude = longitude ?: return null,
            altitude = altitude,
            crs = crs,
            uncertainty = uncertainty,
            parameters = parameters // secondary constructor will apply .asUnmodifiable()
        )
    }
}
