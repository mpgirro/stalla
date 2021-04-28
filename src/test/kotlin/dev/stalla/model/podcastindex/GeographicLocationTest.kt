package dev.stalla.model.podcastindex

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import org.junit.jupiter.api.Test

class GeographicLocationTest {

    @Test
    fun `should parse a Geo URI with latitude and longitude correctly`() {
        assertThat(GeographicLocation.of("geo:37.786971,-122.399677")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(37.786971)
            prop(GeographicLocation::longitude).isEqualTo(-122.399677)
            prop(GeographicLocation::altitude).isNull()
            prop(GeographicLocation::crs).isNull()
            prop(GeographicLocation::uncertainty).isNull()
            prop(GeographicLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `should parse a Geo URI with latitude and longitude and altitude correctly`() {
        assertThat(GeographicLocation.of("geo:40.714623,-74.006605,1.1")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(40.714623)
            prop(GeographicLocation::longitude).isEqualTo(-74.006605)
            prop(GeographicLocation::altitude).isEqualTo(1.1)
            prop(GeographicLocation::crs).isNull()
            prop(GeographicLocation::uncertainty).isNull()
            prop(GeographicLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `test 2`() {
        assertThat(GeographicLocation.of("geo:48.198634,16.371648;crs=wgs84;u=40")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(48.198634)
            prop(GeographicLocation::longitude).isEqualTo(16.371648)
            prop(GeographicLocation::altitude).isNull()
            prop(GeographicLocation::crs).isEqualTo("wgs84")
            prop(GeographicLocation::uncertainty).isEqualTo(40.0)
            prop(GeographicLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse all`() {
        assertThat(GeographicLocation.of("geo:12.34,56.78,-21.43;crs=wgs84;u=12;param=value")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(56.78)
            prop(GeographicLocation::altitude).isEqualTo(-21.43)
            prop(GeographicLocation::crs).isEqualTo("wgs84")
            prop(GeographicLocation::uncertainty).isEqualTo(12.0)
            prop(GeographicLocation::parameters).hasSize(1)
            prop("parameter") { GeographicLocation::parameter.call(it, "param") }.isNotNull().isEqualTo("value")
        }
    }

    @Test
    fun `parse no params`() {
        assertThat(GeographicLocation.of("geo:12.34,56.78,-21.43;crs=wgs84;u=12")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(56.78)
            prop(GeographicLocation::altitude).isEqualTo(-21.43)
            prop(GeographicLocation::crs).isEqualTo("wgs84")
            prop(GeographicLocation::uncertainty).isEqualTo(12.0)
            prop(GeographicLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse no params or u`() {
        assertThat(GeographicLocation.of("geo:12.34,56.78,-21.43;crs=wgs84")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(56.78)
            prop(GeographicLocation::altitude).isEqualTo(-21.43)
            prop(GeographicLocation::crs).isEqualTo("wgs84")
            prop(GeographicLocation::uncertainty).isNull()
            prop(GeographicLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse no params or crs`() {
        assertThat(GeographicLocation.of("geo:12.34,56.78,-21.43;u=12")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(56.78)
            prop(GeographicLocation::altitude).isEqualTo(-21.43)
            prop(GeographicLocation::crs).isNull()
            prop(GeographicLocation::uncertainty).isEqualTo(12.0)
            prop(GeographicLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse no params or u or crs`() {
        assertThat(GeographicLocation.of("geo:12.34,56.78,-21.43")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(56.78)
            prop(GeographicLocation::altitude).isEqualTo(-21.43)
            prop(GeographicLocation::crs).isNull()
            prop(GeographicLocation::uncertainty).isNull()
            prop(GeographicLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse no params or u or crs or coordC`() {
        assertThat(GeographicLocation.of("geo:12.34,56.78")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(56.78)
            prop(GeographicLocation::altitude).isNull()
            prop(GeographicLocation::crs).isNull()
            prop(GeographicLocation::uncertainty).isNull()
            prop(GeographicLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse invalid uncertainty`() {
        assertThat(GeographicLocation.of("geo:12.34,56.78;u=invalid")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(56.78)
            prop(GeographicLocation::altitude).isNull()
            prop(GeographicLocation::crs).isNull()
            prop(GeographicLocation::uncertainty).isNull()
            prop(GeographicLocation::parameters).hasSize(1)
            prop("parameter") { GeographicLocation::parameter.call(it, "u") }.isNotNull().isEqualTo("invalid")
        }
    }

    @Test
    fun `parse no params or u or crs or coordsC or coordB`() {
        assertThat(GeographicLocation.of("geo:12.34")).isNull()
    }

    @Test
    fun `parse no params or u or crs or coordsC or coordB or coordA`() {
        assertThat(GeographicLocation.of("geo:")).isNull()
    }

    @Test
    fun `parse not geo uri(`() {
        assertThat(GeographicLocation.of("https://stalla.dev")).isNull()
    }

    @Test
    fun `parse decode special chars in param value`() {
        assertThat(GeographicLocation.of("geo:12.34,56.78;param=with%20%3d%20special%20&%20chars")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(56.78)
            prop(GeographicLocation::altitude).isNull()
            prop(GeographicLocation::crs).isNull()
            prop(GeographicLocation::uncertainty).isNull()
            prop(GeographicLocation::parameters).hasSize(1)
            prop("parameter") { GeographicLocation::parameter.call(it, "param") }.isNotNull().isEqualTo("with = special & chars")
        }
    }

    @Test
    fun `multiple params`() {
        assertThat(GeographicLocation.of("geo:12.34,45.67,-21.43;crs=theCrs;u=12.0;param=value;param2=value2")).isNotNull().all {
            prop(GeographicLocation::latitude).isEqualTo(12.34)
            prop(GeographicLocation::longitude).isEqualTo(45.67)
            prop(GeographicLocation::altitude).isEqualTo(-21.43)
            prop(GeographicLocation::crs).isEqualTo("theCrs")
            prop(GeographicLocation::uncertainty).isEqualTo(12.0)
            prop(GeographicLocation::parameters).hasSize(2)
            prop("parameter") { GeographicLocation::parameter.call(it, "param") }.isNotNull().isEqualTo("value")
            prop("parameter") { GeographicLocation::parameter.call(it, "param2") }.isNotNull().isEqualTo("value2")
        }
    }

    @Test
    fun `WGS84 pole rule`() {
        val geoLocation1 = GeographicLocation.of("geo:90,-22.43;crs=WGS84")
        val geoLocation2 = GeographicLocation.of("geo:90,46;crs=WGS84")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `should no match two Geo URIs in WGS-84 special pole case if the latitude is different`() {
        val geoLocation1 = GeographicLocation.of("geo:90,10")
        val geoLocation2 = GeographicLocation.of("geo:45,20")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isFalse()
        }
    }

    @Test
    fun `should no match two Geo URIs in WGS-84 special pole case if the latitude has a different sign`() {
        val geoLocation1 = GeographicLocation.of("geo:90,10")
        val geoLocation2 = GeographicLocation.of("geo:-90,10")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isFalse()
        }
    }

    @Test
    fun `should match two Geo URIs in WGS-84 special pole case by ignoring the longitude`() {
        val geoLocation1 = GeographicLocation.of("geo:90,5")
        val geoLocation2 = GeographicLocation.of("geo:90,10")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `should interprete a missing CRS value as WGS-84 when matching two Geo URIs with WGS-84 special pole case`() {
        val geoLocation1 = GeographicLocation.of("geo:90,5")
        val geoLocation2 = GeographicLocation.of("geo:90,10;crs=WGS84")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `should match two Geo URIs in WGS-84 special date line case if the longitude has a different sign`() {
        val geoLocation1 = GeographicLocation.of("geo:10,180")
        val geoLocation2 = GeographicLocation.of("geo:10,-180")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `should interprete a missing CRS value as WGS-84 when matching two Geo URIs with WGS-84 special date line case`() {
        val geoLocation1 = GeographicLocation.of("geo:10,180")
        val geoLocation2 = GeographicLocation.of("geo:10,-180;crs=WGS84")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `parameters bitwise identical after percent-decoding parameter names are case insensitive`() {
        val geoLocation1 = GeographicLocation.of("geo:66,30;u=6.500;FOo=this%2dthat")
        val geoLocation2 = GeographicLocation.of("geo:66.0,30;u=6.5;foo=this-that")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `parameter order is insignificant`() {
        val geoLocation1 = GeographicLocation.of("geo:47,11;foo=blue;bar=white")
        val geoLocation2 = GeographicLocation.of("geo:47,11;bar=white;foo=blue")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `parameter keys are case-insensitive`() {
        val geoLocation1 = GeographicLocation.of("geo:22,0;bar=blue")
        val geoLocation2 = GeographicLocation.of("geo:22,0;BAR=blue")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `parameter values are case-sensitive`() {
        val geoLocation1 = GeographicLocation.of("geo:22,0;bar=BLUE")
        val geoLocation2 = GeographicLocation.of("geo:22,0;bar=blue")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isFalse()
        }
    }
}
