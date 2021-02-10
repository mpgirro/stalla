package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.bitlove.Bitlove
import dev.stalla.util.whenNotNull

/** Builder for constructing [Bitlove] instances. */
public interface EpisodeBitloveBuilder : Builder<Bitlove> {

    /** Set the guid value. */
    public fun guid(guid: String): EpisodeBitloveBuilder

    override fun from(model: Bitlove?): EpisodeBitloveBuilder = whenNotNull(model) { bitlove ->
        guid(bitlove.guid)
    }
}
