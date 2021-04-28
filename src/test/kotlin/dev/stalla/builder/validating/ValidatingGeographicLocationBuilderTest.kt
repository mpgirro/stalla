package dev.stalla.builder.validating

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.GeographicLocationBuilder
import dev.stalla.model.aPodcastindexGeographicLocation
import dev.stalla.model.podcastindex.GeographicLocation
import org.junit.jupiter.api.Test

internal class ValidatingGeographicLocationBuilderTest {

    @Test
    internal fun `should not build a GeographicLocation when the mandatory fields are missing`() {
        val geoBuilder = ValidatingGeographicLocationBuilder()

        assertAll {
            assertThat(geoBuilder).prop(GeographicLocationBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(geoBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a GeographicLocation with all the mandatory href field`() {
        val geoBuilder = ValidatingGeographicLocationBuilder()
            .latitude(1.0)
            .longitude(2.0)

        assertAll {
            assertThat(geoBuilder).prop(GeographicLocationBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(geoBuilder.build()).isNotNull().all {
                prop(GeographicLocation::latitude).isEqualTo(1.0)
                prop(GeographicLocation::longitude).isEqualTo(2.0)
            }
        }
    }

    @Test
    internal fun `should populate a GeographicLocation builder with all properties from an GeographicLocation model`() {
        val geo = aPodcastindexGeographicLocation()
        val geoBuilder = GeographicLocation.builder().applyFrom(geo)

        assertAll {
            assertThat(geoBuilder).prop(GeographicLocationBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(geoBuilder.build()).isNotNull().isEqualTo(geo)
        }
    }
}
