package io.hemin.wien.builder.validating

import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Person

internal class ValidatingPersonBuilder : PersonBuilder {

    private lateinit var nameValue: String

    private var email: String? = null
    private var uri: String? = null

    override fun name(name: String): PersonBuilder = apply { this.nameValue = name }

    override fun email(email: String?): PersonBuilder = apply { this.email = email }

    override fun uri(uri: String?): PersonBuilder = apply { this.uri = uri }

    override val hasEnoughDataToBuild: Boolean
        get() = ::nameValue.isInitialized

    override fun build(): Person? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Person(name = nameValue, email = email, uri = uri)
    }
}
