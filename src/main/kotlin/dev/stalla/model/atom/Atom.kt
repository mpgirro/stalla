package dev.stalla.model.atom

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.validating.ValidatingAtomBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.atom.Atom.Factory.builder

/**
 * Model class for data from elements of the Atom namespace.
 *
 * Direct instantiation from Java is discouraged. Use the [builder] method
 * to obtain a builder instance for expressive construction instead.
 *
 * @property authors List of data from the `<atom:author>` elements as [AtomPerson] instances.
 * @property contributors List of data from the `<atom:contributor>` elements as [AtomPerson] instances.
 * @property links List of data from the `<atom:link>` elements as [Link] instances.
 *
 * @since 1.0.0
 */
public data class Atom(
    val authors: List<AtomPerson>,
    val contributors: List<AtomPerson>,
    val links: List<Link>
) {

    /** Provides a builder for the [Atom] class. */
    public companion object Factory : BuilderFactory<Atom, AtomBuilder> {

        /** Returns a builder implementation for building [Atom] model instances. */
        @JvmStatic
        override fun builder(): AtomBuilder = ValidatingAtomBuilder()
    }
}
