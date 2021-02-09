package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Episode
import dev.stalla.util.whenNotNull

/** Builder for constructing [Episode.Bitlove] instances. */
public interface EpisodeBitloveBuilder : Builder<Episode.Bitlove> {

    /** Set the guid value. */
    public fun guid(guid: String): EpisodeBitloveBuilder

    override fun from(model: Episode.Bitlove?): EpisodeBitloveBuilder = whenNotNull(model) { bitlove ->
        guid(bitlove.guid)
    }
}
