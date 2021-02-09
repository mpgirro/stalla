package dev.stalla.builder

import dev.stalla.model.Person
import dev.stalla.util.whenNotNull

/** Builder for constructing [Person] instances. */
public interface PersonBuilder : Builder<Person> {

    /** Set the name value. */
    public fun name(name: String): PersonBuilder

    /** Set the email value. */
    public fun email(email: String?): PersonBuilder

    /** Set the uri value. */
    public fun uri(uri: String?): PersonBuilder

    override fun from(model: Person?): PersonBuilder = whenNotNull(model) { person ->
        name(person.name)
        email(person.email)
        uri(person.uri)
    }
}
