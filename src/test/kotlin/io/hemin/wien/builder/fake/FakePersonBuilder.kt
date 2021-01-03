package io.hemin.wien.builder.fake

import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Person

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePersonBuilder : FakeBuilder<Person>(), PersonBuilder {

    lateinit var nameValue: String
    var email: String? = null
    var uri: String? = null

    /** Set the name value. */
    override fun name(name: String): PersonBuilder = apply { this.nameValue = name }

    /** Set the email value. */
    override fun email(email: String?): PersonBuilder = apply { this.email = email }

    /** Set the uri value. */
    override fun uri(uri: String?): PersonBuilder = apply { this.uri = uri }
}
