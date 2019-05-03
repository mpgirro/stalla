package io.hemin.wien.builder

import io.hemin.wien.model.Person

class PersonBuilder : Builder<Person> {

    private var name: String?  = null
    private var email: String? = null
    private var uri: String?   = null

    fun name(name: String?) = apply { this.name = name }

    fun email(email: String?) = apply { this.email = email }

    fun uri(uri: String?) = apply { this.uri = uri }

    override fun build(): Person? {
        return if (Builder.anyNotNull(name, email, uri))
            Person(
                name  = name,
                email = email,
                uri   = uri
            )
        else
            null
    }

}
