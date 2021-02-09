package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.Podcast
import dev.stalla.util.whenNotNull

/** Builder for constructing [Podcast.Fyyd] instances. */
public interface PodcastFyydBuilder : Builder<Podcast.Fyyd> {

    /** Set the verify value. */
    public fun verify(verify: String): PodcastFyydBuilder

    override fun from(model: Podcast.Fyyd?): PodcastFyydBuilder = whenNotNull(model) { fyyd ->
        verify(fyyd.verify)
    }
}
