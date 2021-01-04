package io.hemin.wien.builder.fake

import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Person

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePersonBuilder : FakeBuilder<Person>(), PersonBuilder {

    lateinit var nameValue: String
    var email: String? = null
    var uri: String? = null

    override fun name(name: String): PersonBuilder = apply { this.nameValue = name }

    override fun email(email: String?): PersonBuilder = apply { this.email = email }

    override fun uri(uri: String?): PersonBuilder = apply { this.uri = uri }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePersonBuilder) return false

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
}
