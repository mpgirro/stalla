package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastFyydBuilder
import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.Fyyd] instances. */
internal class ValidatingPodcastFyydBuilder : PodcastFyydBuilder {

    private lateinit var verifyValue: String

    /** Set the verify value. */
    override fun verify(verify: String): PodcastFyydBuilder = apply { this.verifyValue = verify }

    override fun build(): Podcast.Fyyd? {
        if (!::verifyValue.isInitialized) {
            return null
        }
        return Podcast.Fyyd(verifyValue)
    }
}
