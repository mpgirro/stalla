package dev.stalla.builder.validating

import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.model.atom.AtomPerson
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingAtomPersonBuilder : AtomPersonBuilder {

    private var name: String? = null

    private var email: String? = null
    private var uri: String? = null

    override fun name(name: String): AtomPersonBuilder = apply { this.name = name }

    override fun email(email: String?): AtomPersonBuilder = apply { this.email = email }

    override fun uri(uri: String?): AtomPersonBuilder = apply { this.uri = uri }

    override val hasEnoughDataToBuild: Boolean
        get() = name != null

    override fun build(): AtomPerson? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return AtomPerson(
            name = checkRequiredProperty(::name),
            email = email,
            uri = uri
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingAtomPersonBuilder) return false

        if (name != other.name) return false
        if (email != other.email) return false
        if (uri != other.uri) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (uri?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "ValidatingAtomPersonBuilder(name='$name', email=$email, uri=$uri)"
}
