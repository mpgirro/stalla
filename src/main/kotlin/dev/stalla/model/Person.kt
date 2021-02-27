package dev.stalla.model

import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.validating.ValidatingPersonBuilder

/**
 * Model class for elements describing persons.
 *
 * @property name The name of the person.
 * @property email The email of the person.
 * @property uri The uri of the person.
 */
public data class Person(
    val name: String,
    val email: String? = null,
    val uri: String? = null
) {

    /** Provides a builder for the [Person] class. */
    public companion object Factory : BuilderFactory<Person, PersonBuilder> {

        /** Returns a builder implementation for building [Person] model instances. */
        @JvmStatic
        override fun builder(): PersonBuilder = ValidatingPersonBuilder()
    }
}
