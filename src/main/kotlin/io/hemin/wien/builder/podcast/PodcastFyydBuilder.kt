package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast

internal interface PodcastFyydBuilder : Builder<Podcast.Fyyd> {

    /** Set the verify value. */
    fun verify(verify: String): PodcastFyydBuilder
}
