package io.hemin.wien.builder

import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.Fyyd] instances. */
class PodcastFyydBuilder : Builder<Podcast.Fyyd> {

    private var verify: String? = null

    /** Set the verify value. */
    fun verify(verify: String?) = apply { this.verify = verify }

    override fun build(): Podcast.Fyyd? {
        return if (anyNotNull(verify)) {
            Podcast.Fyyd(
                verify = verify
            )
        } else {
            null
        }
    }
}
