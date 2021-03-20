package dev.stalla.builder

import dev.stalla.model.atom.AtomPerson
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [AtomPerson] instances.
 *
 * @since 1.0.0
 */
public interface AtomPersonBuilder : Builder<AtomPerson> {

    /** Set the name value. */
    public fun name(name: String): AtomPersonBuilder

    /** Set the email value. */
    public fun email(email: String?): AtomPersonBuilder

    /** Set the uri value. */
    public fun uri(uri: String?): AtomPersonBuilder

    override fun applyFrom(prototype: AtomPerson?): AtomPersonBuilder =
        whenNotNull(prototype) { person ->
            name(person.name)
            email(person.email)
            uri(person.uri)
        }
}
