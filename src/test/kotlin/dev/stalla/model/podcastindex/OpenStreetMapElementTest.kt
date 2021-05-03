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
    fun `should parse an OpenStreetMap element with Relation type correctly`() {
        assertThat(OpenStreetMapElement.of("R148838")).isNotNull().all {
            prop(OpenStreetMapElement::type).isEqualTo(OpenStreetMapElementType.Relation)
            prop(OpenStreetMapElement::id).isEqualTo(148838)
            prop(OpenStreetMapElement::revision).isNull()
        }
    }

    @Test
    fun `should parse an OpenStreetMap element with Way type correctly`() {
        assertThat(OpenStreetMapElement.of("W5013364")).isNotNull().all {
            prop(OpenStreetMapElement::type).isEqualTo(OpenStreetMapElementType.Way)
            prop(OpenStreetMapElement::id).isEqualTo(5013364)
            prop(OpenStreetMapElement::revision).isNull()
        }
    }

    @Test
    fun `should parse an OpenStreetMap element with Node type correctly`() {
        assertThat(OpenStreetMapElement.of("N45678")).isNotNull().all {
            prop(OpenStreetMapElement::type).isEqualTo(OpenStreetMapElementType.Node)
            prop(OpenStreetMapElement::id).isEqualTo(45678)
            prop(OpenStreetMapElement::revision).isNull()
        }
    }

    @Test
    fun `should parse an OpenStreetMap element with revision correctly`() {
        assertThat(OpenStreetMapElement.of("R7444#188")).isNotNull().all {
            prop(OpenStreetMapElement::type).isEqualTo(OpenStreetMapElementType.Relation)
            prop(OpenStreetMapElement::id).isEqualTo(7444)
            prop(OpenStreetMapElement::revision).isEqualTo(188)
        }
    }

    @Test
    fun `should not parse an OpenStreetMap element when there is only an invalid type`() {
        assertThat(OpenStreetMapElement.of("X")).isNull()
    }

    @Test
    fun `should not parse an OpenStreetMap element when the type is invalid`() {
        assertThat(OpenStreetMapElement.of("X12345")).isNull()
    }

    @Test
    fun `should not parse an OpenStreetMap element when there is only a valid type`() {
        assertThat(OpenStreetMapElement.of("R")).isNull()
    }

    @Test
    fun `should not parse an OpenStreetMap element when there is a valid type and a revision but the ID is missing`() {
        assertThat(OpenStreetMapElement.of("R#188")).isNull()
    }

    @Test
    fun `should not parse an OpenStreetMap element when the ID is not numeric`() {
        assertThat(OpenStreetMapElement.of("Rabc")).isNull()
    }

    @Test
    fun `should not parse an OpenStreetMap element with revision when the ID is not numeric`() {
        assertThat(OpenStreetMapElement.of("Rabc#123")).isNull()
    }

    @Test
    fun `should not parse an OpenStreetMap element when the revison is not numeric`() {
        assertThat(OpenStreetMapElement.of("R123#abc")).isNull()
    }
}
