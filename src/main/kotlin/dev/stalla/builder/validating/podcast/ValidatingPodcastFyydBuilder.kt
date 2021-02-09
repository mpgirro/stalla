package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.model.Podcast

internal class ValidatingPodcastFyydBuilder : PodcastFyydBuilder {

    private lateinit var verifyValue: String

    override fun verify(verify: String): PodcastFyydBuilder = apply { this.verifyValue = verify }

    override val hasEnoughDataToBuild: Boolean
        get() = ::verifyValue.isInitialized

    override fun build(): Podcast.Fyyd? {
        if (!hasEnoughDataToBuild) {
            return null
        }
        return Podcast.Fyyd(verifyValue)
    }
}
