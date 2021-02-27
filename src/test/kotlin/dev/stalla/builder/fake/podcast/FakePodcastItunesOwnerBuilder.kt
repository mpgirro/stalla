package dev.stalla.builder.fake.podcast

import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastItunesOwnerBuilder
import dev.stalla.model.itunes.ItunesOwner

internal class FakePodcastItunesOwnerBuilder : FakeBuilder<ItunesOwner>(), PodcastItunesOwnerBuilder {

    var nameValue: String? = null
    var emailValue: String? = null

    override fun name(name: String): PodcastItunesOwnerBuilder = apply { this.nameValue = name }

    override fun email(email: String): PodcastItunesOwnerBuilder = apply { this.emailValue = email }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastItunesOwnerBuilder) return false

        if (nameValue != other.nameValue) return false
        if (emailValue != other.emailValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nameValue.hashCode()
        result = 31 * result + emailValue.hashCode()
        return result
    }

    override fun toString() = "FakePersonBuilder(nameValue='$nameValue', emailValue=$emailValue)"
}
