package io.hemin.wien.builder

import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.Fyyd] instances. */
class PodcastFyydBuilder : Builder<Podcast.Fyyd> {

    private lateinit var verifyValue: String

    /** Set the verify value. */
    fun verify(verify: String) = apply { this.verifyValue = verify }

    override fun build(): Podcast.Fyyd {
        require(::verifyValue.isInitialized) { "The podcast fyyd:verify is mandatory" }
        return Podcast.Fyyd(verifyValue)
    }
}
