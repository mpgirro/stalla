package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Podcast

interface PodcastFyydBuilder : Builder<Podcast.Fyyd> {

    /** Set the verify value. */
    fun verify(verify: String): PodcastFyydBuilder
}
