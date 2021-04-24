package dev.stalla.parser

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.model.podcastindex.GeoLocation
import org.junit.jupiter.api.Test

class GeoUriParserTest {

    @Test
    fun `should parse a Geo URI with A and B correctly`() {
        assertAll {
            assertThat(GeoUriParser.parse("geo:37.786971,-122.399677")).isNotNull().all {
                prop(GeoLocation::coordA).isEqualTo(37.786971)
                prop(GeoLocation::coordB).isEqualTo(-122.399677)
                prop(GeoLocation::coordC).isNull()
                prop(GeoLocation::parameters).isEmpty()
            }
        }
    }

    @Test
    fun `test 2`() {
        assertAll {
            assertThat(GeoUriParser.parse("geo:48.198634,16.371648;crs=wgs84;u=40")).isNotNull().all {
                prop(GeoLocation::coordA).isEqualTo(48.198634)
                prop(GeoLocation::coordB).isEqualTo(16.371648)
                prop(GeoLocation::coordC).isNull()
                prop(GeoLocation::parameters).isEmpty()
            }
        }
    }
}
