package dev.stalla.model.atom

import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.builder.validating.ValidatingAtomPersonBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.atom.Atom.Factory.builder
import dev.stalla.model.atom.AtomPerson.Factory.builder

/**
 * Model class for elements describing persons.
 *
 * Direct instantiation from Java is discouraged. Use the [builder] method
 * to obtain a builder instance for expressive construction instead.
 *
 * @property name The name of the person.
 * @property email The email of the person.
 * @property uri The uri of the person.
 *
 * @since 1.0.0
 */
public data class AtomPerson(
    val name: String,
    val email: String? = null,
    val uri: String? = null
) {

    /** Provides a builder for the [AtomPerson] class. */
    public companion object Factory : BuilderFactory<AtomPerson, AtomPersonBuilder> {

        /** Returns a builder implementation for building [AtomPerson] model instances. */
        @JvmStatic
        override fun builder(): AtomPersonBuilder = ValidatingAtomPersonBuilder()
    }
}
