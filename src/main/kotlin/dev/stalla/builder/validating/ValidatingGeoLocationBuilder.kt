package dev.stalla.builder.validating

import dev.stalla.builder.GeoLocationBuilder
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.GeoLocation.Factory.CRS_PARAM
import dev.stalla.model.podcastindex.GeoLocation.Factory.UNCERTAINTY_PARAM
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingGeoLocationBuilder : GeoLocationBuilder {

    private var coordA: Double? = null
    private var coordB: Double? = null
    private var coordC: Double? = null
    private var crs: String? = null
    private var uncertainty: Double? = null
    private var parameters: MutableMap<String, String> = mutableMapOf()

    override fun coordA(coordA: Double): GeoLocationBuilder = apply { this.coordA = coordA }

    override fun coordB(coordB: Double): GeoLocationBuilder = apply { this.coordB = coordB }

    override fun coordC(coordC: Double?): GeoLocationBuilder = apply { this.coordC = coordC }

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

    override fun hasCoordA(): Boolean = coordA != null

    override fun hasCoordB(): Boolean = coordB != null

    override fun hasCoordC(): Boolean = coordC != null

    override val hasEnoughDataToBuild: Boolean
        get() = coordA != null && coordB != null

    override fun build(): GeoLocation? {
        if (!hasEnoughDataToBuild) return null

        return GeoLocation(
            coordA = coordA ?: return null,
            coordB = coordB ?: return null,
            coordC = coordC,
            crs = crs,
            uncertainty = uncertainty,
            parameters = parameters // this secondary constructor will apply .asUnmodifiable()
        )
    }

}
