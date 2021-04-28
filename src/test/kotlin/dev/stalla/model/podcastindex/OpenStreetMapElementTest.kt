package dev.stalla.model.podcastindex

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import org.junit.jupiter.api.Test

class OpenStreetMapElementTest {

    @Test
    fun `test 1`() {
        assertThat(OpenStreetMapElement.of("R148838")).isNotNull().all {
            prop(OpenStreetMapElement::type).isEqualTo(OpenStreetMapElementType.Relation)
            prop(OpenStreetMapElement::id).isEqualTo(148838)
            prop(OpenStreetMapElement::revision).isNull()
        }
    }

    @Test
    fun `test 2`() {
        assertThat(OpenStreetMapElement.of("W5013364")).isNotNull().all {
            prop(OpenStreetMapElement::type).isEqualTo(OpenStreetMapElementType.Way)
            prop(OpenStreetMapElement::id).isEqualTo(5013364)
            prop(OpenStreetMapElement::revision).isNull()
        }
    }

    @Test
    fun `test 3`() {
        assertThat(OpenStreetMapElement.of("R7444#188")).isNotNull().all {
            prop(OpenStreetMapElement::type).isEqualTo(OpenStreetMapElementType.Relation)
            prop(OpenStreetMapElement::id).isEqualTo(7444)
            prop(OpenStreetMapElement::revision).isEqualTo(188)
        }
    }

    @Test
    fun `test 3_5`() {
        assertThat(OpenStreetMapElement.of("X")).isNull()
    }

    @Test
    fun `test 4`() {
        assertThat(OpenStreetMapElement.of("X12345")).isNull()
    }

    @Test
    fun `test 5`() {
        assertThat(OpenStreetMapElement.of("R")).isNull()
    }

    @Test
    fun `test 6`() {
        assertThat(OpenStreetMapElement.of("R#188")).isNull()
    }

    @Test
    fun `test 7`() {
        assertThat(OpenStreetMapElement.of("Rabc")).isNull()
    }

    @Test
    fun `test 8`() {
        assertThat(OpenStreetMapElement.of("Rabc")).isNull()
    }

    @Test
    fun `test 9`() {
        assertThat(OpenStreetMapElement.of("Rabc#123")).isNull()
    }
}
