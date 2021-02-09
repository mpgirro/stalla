package dev.stalla.builder.validating

import dev.stalla.builder.PersonBuilder
import dev.stalla.model.Person

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
