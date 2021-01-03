package io.hemin.wien.builder.validating

import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Person

/** Builder class for [Person] instances. */
internal class ValidatingPersonBuilder : PersonBuilder {

    private lateinit var nameValue: String

    private var email: String? = null
    private var uri: String? = null

    /** Set the name value. */
    override fun name(name: String): PersonBuilder = apply { this.nameValue = name }

    /** Set the email value. */
    override fun email(email: String?): PersonBuilder = apply { this.email = email }

    /** Set the uri value. */
    override fun uri(uri: String?): PersonBuilder = apply { this.uri = uri }

    override fun build(): Person? {
        if (!::nameValue.isInitialized) {
            return null
        }

        return Person(name = nameValue, email = email, uri = uri)
    }
}
