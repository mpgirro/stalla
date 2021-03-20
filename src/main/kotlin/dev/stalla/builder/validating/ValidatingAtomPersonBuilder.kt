package dev.stalla.builder.validating

import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.model.atom.AtomPerson
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingAtomPersonBuilder : AtomPersonBuilder {

    private lateinit var nameValue: String

    private var email: String? = null
    private var uri: String? = null

    override fun name(name: String): AtomPersonBuilder = apply { this.nameValue = name }

    override fun email(email: String?): AtomPersonBuilder = apply { this.email = email }

    override fun uri(uri: String?): AtomPersonBuilder = apply { this.uri = uri }

    override val hasEnoughDataToBuild: Boolean
        get() = ::nameValue.isInitialized

    override fun build(): AtomPerson? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return AtomPerson(
            name = nameValue,
            email = email,
            uri = uri
        )
    }
}
