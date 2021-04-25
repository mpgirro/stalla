package dev.stalla.parser

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.OsmType
import org.junit.jupiter.api.Test
import java.math.BigInteger

class OsmFeatureParserTest {

    @Test
    fun `test 1`() {
        assertThat(OsmFeatureParser.parse("R148838")).isNotNull().all {
            prop(OpenStreetMapFeature::type).isEqualTo(OsmType.Relation)
            prop(OpenStreetMapFeature::id).isEqualTo(BigInteger("148838"))
            prop(OpenStreetMapFeature::revision).isNull()
        }
    }

    @Test
    fun `test 2`() {
        assertThat(OsmFeatureParser.parse("W5013364")).isNotNull().all {
            prop(OpenStreetMapFeature::type).isEqualTo(OsmType.Way)
            prop(OpenStreetMapFeature::id).isEqualTo(BigInteger("5013364"))
            prop(OpenStreetMapFeature::revision).isNull()
        }
    }

    @Test
    fun `test 3`() {
        assertThat(OsmFeatureParser.parse("R7444#188")).isNotNull().all {
            prop(OpenStreetMapFeature::type).isEqualTo(OsmType.Relation)
            prop(OpenStreetMapFeature::id).isEqualTo(BigInteger("7444"))
            prop(OpenStreetMapFeature::revision).isEqualTo("188")
        }
    }

    @Test
    fun `test 3_5`() {
        assertThat(OsmFeatureParser.parse("X")).isNull()
    }

    @Test
    fun `test 4`() {
        assertThat(OsmFeatureParser.parse("X12345")).isNull()
    }

    @Test
    fun `test 5`() {
        assertThat(OsmFeatureParser.parse("R")).isNull()
    }

    @Test
    fun `test 6`() {
        assertThat(OsmFeatureParser.parse("R#188")).isNull()
    }

    @Test
    fun `test 7`() {
        assertThat(OsmFeatureParser.parse("Rabc")).isNull()
    }

    @Test
    fun `test 8`() {
        assertThat(OsmFeatureParser.parse("Rabc")).isNull()
    }

    @Test
    fun `test 9`() {
        assertThat(OsmFeatureParser.parse("Rabc#123")).isNull()
    }

}
