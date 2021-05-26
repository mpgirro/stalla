package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastItunesOwnerBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.itunes.ItunesOwner
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingPodcastItunesOwnerBuilder : PodcastItunesOwnerBuilder {

    private var name: String? = null
    private var email: String? = null

    override fun name(name: String): PodcastItunesOwnerBuilder = apply { this.name = name }

    override fun email(email: String): PodcastItunesOwnerBuilder = apply { this.email = email }

    override val hasEnoughDataToBuild: Boolean
        get() = name != null && email != null

    override fun build(): ItunesOwner? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return ItunesOwner(
            name = checkRequiredProperty(::name),
            email = checkRequiredProperty(::email)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastItunesOwnerBuilder) return false

        if (name != other.name) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String = "ValidatingPodcastItunesOwnerBuilder(name='$name', email='$email')"
}
