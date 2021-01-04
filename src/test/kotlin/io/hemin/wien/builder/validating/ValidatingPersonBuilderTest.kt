package io.hemin.wien.builder.validating

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Person
import org.junit.jupiter.api.Test

internal class ValidatingPersonBuilderTest {

    @Test
    internal fun `should not build a person when the mandatory fields are missing`() {
        val personBuilder = ValidatingPersonBuilder()

        assertThat(personBuilder.build()).isNull()
    }

    @Test
    internal fun `should build a person with all the mandatory fields`() {
        val personBuilder = ValidatingPersonBuilder()
            .name("name")

        assertThat(personBuilder.build()).isNotNull().all {
            prop(Person::name).isEqualTo("name")
            prop(Person::email).isNull()
            prop(Person::uri).isNull()
        }
    }

    @Test
    internal fun `should build a person with all the optional fields`() {
        val personBuilder = ValidatingPersonBuilder()
            .name("name")
            .email("email")
            .uri("uri")

        assertThat(personBuilder.build()).isNotNull().all {
            prop(Person::name).isEqualTo("name")
            prop(Person::email).isEqualTo("email")
            prop(Person::uri).isEqualTo("uri")
        }
    }
}
