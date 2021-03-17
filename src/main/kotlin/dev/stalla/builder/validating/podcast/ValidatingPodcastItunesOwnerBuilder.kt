package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastItunesOwnerBuilder
import dev.stalla.model.itunes.ItunesOwner
import dev.stalla.util.InternalAPI2

@InternalAPI2
internal class ValidatingPodcastItunesOwnerBuilder : PodcastItunesOwnerBuilder {

    private lateinit var nameValue: String
    private lateinit var emailValue: String

    override fun name(name: String): PodcastItunesOwnerBuilder = apply { this.nameValue = name }

    override fun email(email: String): PodcastItunesOwnerBuilder = apply { this.emailValue = email }

    override val hasEnoughDataToBuild: Boolean
        get() = ::nameValue.isInitialized && ::emailValue.isInitialized

    override fun build(): ItunesOwner? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return ItunesOwner(
            name = nameValue,
            email = emailValue
        )
    }
}
