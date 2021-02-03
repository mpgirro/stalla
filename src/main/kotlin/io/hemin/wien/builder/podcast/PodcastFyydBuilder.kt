package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Podcast.Fyyd] instances. */
interface PodcastFyydBuilder : Builder<Podcast.Fyyd> {

    /** Set the verify value. */
    fun verify(verify: String): PodcastFyydBuilder

    override fun from(model: Podcast.Fyyd?): PodcastFyydBuilder = whenNotNull(model) { fyyd ->
        verify(fyyd.verify)
    }
}
