package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [Fyyd] instances.
 *
 * @since 1.0.0
 */
public interface PodcastFyydBuilder : Builder<Fyyd> {

    /** Set the verify value. */
    public fun verify(verify: String): PodcastFyydBuilder

    override fun applyFrom(prototype: Fyyd?): PodcastFyydBuilder =
        whenNotNull(prototype) { fyyd -> verify(fyyd.verify) }
}
