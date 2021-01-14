package io.hemin.wien.model

import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder

/**
 * Model class for elements describing persons.
 *
 * @property name The name of the person.
 * @property email The email of the person.
 * @property uri The uri of the person.
 */
data class Person(
    val name: String,
    val email: String? = null,
    val uri: String? = null
) {
    companion object Factory : BuilderFactory<Person, PersonBuilder> {
        @JvmStatic override fun builder(): PersonBuilder = ValidatingPersonBuilder()
    }
}

