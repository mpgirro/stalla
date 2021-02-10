package dev.stalla.model

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.validating.ValidatingAtomBuilder
import dev.stalla.model.atom.Link

/**
 * Model class for data from elements of the Atom namespace.
 *
 * @property authors List of data from the `<atom:author>` elements as [Person] instances.
 * @property contributors List of data from the `<atom:contributor>` elements as [Person] instances.
 * @property links List of data from the `<atom:link>` elements as [Link] instances.
 */
public data class Atom(
    val authors: List<Person>,
    val contributors: List<Person>,
    val links: List<Link>
) {

    public companion object Factory : BuilderFactory<Atom, AtomBuilder> {

        /** Returns a builder implementation for building [Atom] model instances. */
        @JvmStatic
        override fun builder(): AtomBuilder = ValidatingAtomBuilder()
    }
}
