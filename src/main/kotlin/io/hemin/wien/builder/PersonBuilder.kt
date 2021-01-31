package io.hemin.wien.builder

import io.hemin.wien.model.Link
import io.hemin.wien.model.Person

/** Builder for constructing [Link] instances. */
interface PersonBuilder : Builder<Person> {

    /** Set the name value. */
    fun name(name: String): PersonBuilder

    /** Set the email value. */
    fun email(email: String?): PersonBuilder

    /** Set the uri value. */
    fun uri(uri: String?): PersonBuilder
}
