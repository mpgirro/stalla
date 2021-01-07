package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastFyydBuilder
import io.hemin.wien.model.Podcast

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
