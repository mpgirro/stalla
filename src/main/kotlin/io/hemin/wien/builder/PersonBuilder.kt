package io.hemin.wien.builder

import io.hemin.wien.model.Person

/** Builder class for [Person] instances. */
class PersonBuilder : Builder<Person> {

    private lateinit var nameValue: String
    private var email: String? = null
    private var uri: String? = null

    /** Set the name value. */
    fun name(name: String) = apply { this.nameValue = name }

    /** Set the email value. */
    fun email(email: String?) = apply { this.email = email }

    /** Set the uri value. */
    fun uri(uri: String?) = apply { this.uri = uri }

    override fun build(): Person {
        require(::nameValue.isInitialized) { "The person name is mandatory" }
        return Person(name = nameValue, email = email, uri = uri)
    }
}
