package dev.stalla.model

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
import dev.stalla.model.podcastindex.GeoLocation
import org.junit.jupiter.api.Test

class GeoLocationTest {

    @Test
    fun `should parse a Geo URI with latitude and longitude correctly`() {
        assertThat(GeoLocation.of("geo:37.786971,-122.399677")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(37.786971)
            prop(GeoLocation::coordB).isEqualTo(-122.399677)
            prop(GeoLocation::coordC).isNull()
            prop(GeoLocation::crs).isNull()
            prop(GeoLocation::uncertainty).isNull()
            prop(GeoLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `should parse a Geo URI with latitude and longitude and altitude correctly`() {
        assertThat(GeoLocation.of("geo:40.714623,-74.006605,1.1")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(40.714623)
            prop(GeoLocation::coordB).isEqualTo(-74.006605)
            prop(GeoLocation::coordC).isEqualTo(1.1)
            prop(GeoLocation::crs).isNull()
            prop(GeoLocation::uncertainty).isNull()
            prop(GeoLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `test 2`() {
        assertThat(GeoLocation.of("geo:48.198634,16.371648;crs=wgs84;u=40")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(48.198634)
            prop(GeoLocation::coordB).isEqualTo(16.371648)
            prop(GeoLocation::coordC).isNull()
            prop(GeoLocation::crs).isEqualTo("wgs84")
            prop(GeoLocation::uncertainty).isEqualTo(40.0)
            prop(GeoLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse all`() {
        assertThat(GeoLocation.of("geo:12.34,56.78,-21.43;crs=wgs84;u=12;param=value")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(56.78)
            prop(GeoLocation::coordC).isEqualTo(-21.43)
            prop(GeoLocation::crs).isEqualTo("wgs84")
            prop(GeoLocation::uncertainty).isEqualTo(12.0)
            prop(GeoLocation::parameters).hasSize(1)
            prop("parameter") { GeoLocation::parameter.call(it, "param") }.isNotNull().isEqualTo("value")
        }
    }

    @Test
    fun `parse no params`() {
        assertThat(GeoLocation.of("geo:12.34,56.78,-21.43;crs=wgs84;u=12")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(56.78)
            prop(GeoLocation::coordC).isEqualTo(-21.43)
            prop(GeoLocation::crs).isEqualTo("wgs84")
            prop(GeoLocation::uncertainty).isEqualTo(12.0)
            prop(GeoLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse no params or u`() {
        assertThat(GeoLocation.of("geo:12.34,56.78,-21.43;crs=wgs84")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(56.78)
            prop(GeoLocation::coordC).isEqualTo(-21.43)
            prop(GeoLocation::crs).isEqualTo("wgs84")
            prop(GeoLocation::uncertainty).isNull()
            prop(GeoLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse no params or crs`() {
        assertThat(GeoLocation.of("geo:12.34,56.78,-21.43;u=12")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(56.78)
            prop(GeoLocation::coordC).isEqualTo(-21.43)
            prop(GeoLocation::crs).isNull()
            prop(GeoLocation::uncertainty).isEqualTo(12.0)
            prop(GeoLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse no params or u or crs`() {
        assertThat(GeoLocation.of("geo:12.34,56.78,-21.43")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(56.78)
            prop(GeoLocation::coordC).isEqualTo(-21.43)
            prop(GeoLocation::crs).isNull()
            prop(GeoLocation::uncertainty).isNull()
            prop(GeoLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse no params or u or crs or coordC`() {
        assertThat(GeoLocation.of("geo:12.34,56.78")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(56.78)
            prop(GeoLocation::coordC).isNull()
            prop(GeoLocation::crs).isNull()
            prop(GeoLocation::uncertainty).isNull()
            prop(GeoLocation::parameters).isEmpty()
        }
    }

    @Test
    fun `parse invalid uncertainty`() {
        assertThat(GeoLocation.of("geo:12.34,56.78;u=invalid")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(56.78)
            prop(GeoLocation::coordC).isNull()
            prop(GeoLocation::crs).isNull()
            prop(GeoLocation::uncertainty).isNull()
            prop(GeoLocation::parameters).hasSize(1)
            prop("parameter") { GeoLocation::parameter.call(it, "u") }.isNotNull().isEqualTo("invalid")
        }
    }

    @Test
    fun `parse no params or u or crs or coordsC or coordB`() {
        assertThat(GeoLocation.of("geo:12.34")).isNull()
    }

    @Test
    fun `parse no params or u or crs or coordsC or coordB or coordA`() {
        assertThat(GeoLocation.of("geo:")).isNull()
    }

    @Test
    fun `parse not geo uri(`() {
        assertThat(GeoLocation.of("https://stalla.dev")).isNull()
    }

    @Test
    fun `parse decode special chars in param value`() {
        assertThat(GeoLocation.of("geo:12.34,56.78;param=with%20%3d%20special%20&%20chars")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(56.78)
            prop(GeoLocation::coordC).isNull()
            prop(GeoLocation::crs).isNull()
            prop(GeoLocation::uncertainty).isNull()
            prop(GeoLocation::parameters).hasSize(1)
            prop("parameter") { GeoLocation::parameter.call(it, "param") }.isNotNull().isEqualTo("with = special & chars")
        }
    }

    @Test
    fun `multiple params`() {
        assertThat(GeoLocation.of("geo:12.34,45.67,-21.43;crs=theCrs;u=12.0;param=value;param2=value2")).isNotNull().all {
            prop(GeoLocation::coordA).isEqualTo(12.34)
            prop(GeoLocation::coordB).isEqualTo(45.67)
            prop(GeoLocation::coordC).isEqualTo(-21.43)
            prop(GeoLocation::crs).isEqualTo("theCrs")
            prop(GeoLocation::uncertainty).isEqualTo(12.0)
            prop(GeoLocation::parameters).hasSize(2)
            prop("parameter") { GeoLocation::parameter.call(it, "param") }.isNotNull().isEqualTo("value")
            prop("parameter") { GeoLocation::parameter.call(it, "param2") }.isNotNull().isEqualTo("value2")
        }
    }

    @Test
    fun `WGS84 pole rule`() {
        val geoLocation1 = GeoLocation.of("geo:90,-22.43;crs=WGS84")
        val geoLocation2 = GeoLocation.of("geo:90,46;crs=WGS84")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `parameters bitwise identical after percent-decoding parameter names are case insensitive`() {
        val geoLocation1 = GeoLocation.of("geo:66,30;u=6.500;FOo=this%2dthat")
        val geoLocation2 = GeoLocation.of("geo:66.0,30;u=6.5;foo=this-that")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `parameter order is insignificant`() {
        val geoLocation1 = GeoLocation.of("geo:47,11;foo=blue;bar=white")
        val geoLocation2 = GeoLocation.of("geo:47,11;bar=white;foo=blue")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `parameter keys are case-insensitive`() {
        val geoLocation1 = GeoLocation.of("geo:22,0;bar=blue")
        val geoLocation2 = GeoLocation.of("geo:22,0;BAR=blue")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isTrue()
        }
    }

    @Test
    fun `parameter values are case-sensitive`() {
        val geoLocation1 = GeoLocation.of("geo:22,0;bar=BLUE")
        val geoLocation2 = GeoLocation.of("geo:22,0;bar=blue")
        assertAll {
            assertThat(geoLocation1).isNotNull()
            assertThat(geoLocation2).isNotNull()
            assertThat(geoLocation1!!.match(geoLocation2!!)).isFalse()
        }
    }

}
