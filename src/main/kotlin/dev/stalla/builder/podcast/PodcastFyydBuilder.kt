package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.util.whenNotNull

/** Builder for constructing [Fyyd] instances. */
public interface PodcastFyydBuilder : Builder<Fyyd> {

    /** Set the verify value. */
    public fun verify(verify: String): PodcastFyydBuilder

    override fun from(model: Fyyd?): PodcastFyydBuilder = whenNotNull(model) { fyyd ->
        verify(fyyd.verify)
    }
}
