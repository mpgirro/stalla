package dev.stalla.model

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

class OpenStreetMapFeatureTest {

    @Test
    fun `test 1`() {
        assertThat(OpenStreetMapFeature.of("R148838")).isNotNull().all {
            prop(OpenStreetMapFeature::type).isEqualTo(OsmType.Relation)
            prop(OpenStreetMapFeature::id).isEqualTo(BigInteger("148838"))
            prop(OpenStreetMapFeature::revision).isNull()
        }
    }

    @Test
    fun `test 2`() {
        assertThat(OpenStreetMapFeature.of("W5013364")).isNotNull().all {
            prop(OpenStreetMapFeature::type).isEqualTo(OsmType.Way)
            prop(OpenStreetMapFeature::id).isEqualTo(BigInteger("5013364"))
            prop(OpenStreetMapFeature::revision).isNull()
        }
    }

    @Test
    fun `test 3`() {
        assertThat(OpenStreetMapFeature.of("R7444#188")).isNotNull().all {
            prop(OpenStreetMapFeature::type).isEqualTo(OsmType.Relation)
            prop(OpenStreetMapFeature::id).isEqualTo(BigInteger("7444"))
            prop(OpenStreetMapFeature::revision).isEqualTo(BigInteger("188"))
        }
    }

    @Test
    fun `test 3_5`() {
        assertThat(OpenStreetMapFeature.of("X")).isNull()
    }

    @Test
    fun `test 4`() {
        assertThat(OpenStreetMapFeature.of("X12345")).isNull()
    }

    @Test
    fun `test 5`() {
        assertThat(OpenStreetMapFeature.of("R")).isNull()
    }

    @Test
    fun `test 6`() {
        assertThat(OpenStreetMapFeature.of("R#188")).isNull()
    }

    @Test
    fun `test 7`() {
        assertThat(OpenStreetMapFeature.of("Rabc")).isNull()
    }

    @Test
    fun `test 8`() {
        assertThat(OpenStreetMapFeature.of("Rabc")).isNull()
    }

    @Test
    fun `test 9`() {
        assertThat(OpenStreetMapFeature.of("Rabc#123")).isNull()
    }

}
