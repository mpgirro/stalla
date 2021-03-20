package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.bitlove.Bitlove
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [Bitlove] instances.
 *
 * @since 1.0.0
 */
public interface EpisodeBitloveBuilder : Builder<Bitlove> {

    /** Set the guid value. */
    public fun guid(guid: String): EpisodeBitloveBuilder

    override fun applyFrom(prototype: Bitlove?): EpisodeBitloveBuilder =
        whenNotNull(prototype) { bitlove -> guid(bitlove.guid) }
}
