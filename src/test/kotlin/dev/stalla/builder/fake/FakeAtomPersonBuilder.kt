package dev.stalla.builder.fake

import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.model.atom.AtomPerson

internal class FakeAtomPersonBuilder : FakeBuilder<AtomPerson>(), AtomPersonBuilder {

    var nameValue: String? = null
    var email: String? = null
    var uri: String? = null

    override fun name(name: String): AtomPersonBuilder = apply { this.nameValue = name }

    override fun email(email: String?): AtomPersonBuilder = apply { this.email = email }

    override fun uri(uri: String?): AtomPersonBuilder = apply { this.uri = uri }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeAtomPersonBuilder) return false

        if (nameValue != other.nameValue) return false
        if (email != other.email) return false
        if (uri != other.uri) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nameValue.hashCode()
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (uri?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakePersonBuilder(nameValue='$nameValue', email=$email, uri=$uri)"
}
