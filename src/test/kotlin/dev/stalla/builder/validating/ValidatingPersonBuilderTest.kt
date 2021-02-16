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
import dev.stalla.builder.PersonBuilder
import dev.stalla.model.Person
import dev.stalla.model.aPerson
import org.junit.jupiter.api.Test

internal class ValidatingPersonBuilderTest {

    @Test
    internal fun `should not build a person when the mandatory fields are missing`() {
        val personBuilder = ValidatingPersonBuilder()

        assertAll {
            assertThat(personBuilder).prop(PersonBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(personBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a person with all the mandatory fields`() {
        val personBuilder = ValidatingPersonBuilder()
            .name("name")

        assertAll {
            assertThat(personBuilder).prop(PersonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(personBuilder.build()).isNotNull().all {
                prop(Person::name).isEqualTo("name")
                prop(Person::email).isNull()
                prop(Person::uri).isNull()
            }
        }
    }

    @Test
    internal fun `should build a person with all the optional fields`() {
        val personBuilder = ValidatingPersonBuilder()
            .name("name")
            .email("email")
            .uri("uri")

        assertAll {
            assertThat(personBuilder).prop(PersonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(personBuilder.build()).isNotNull().all {
                prop(Person::name).isEqualTo("name")
                prop(Person::email).isEqualTo("email")
                prop(Person::uri).isEqualTo("uri")
            }
        }
    }

    @Test
    internal fun `should populate a Person builder with all properties from a Person model`() {
        val person = aPerson()
        val personBuilder = Person.builder().applyFrom(person)

        assertAll {
            assertThat(personBuilder).prop(PersonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(personBuilder.build()).isNotNull().isEqualTo(person)
        }
    }
}
