package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.Bitlove] instances. */
public interface EpisodeBitloveBuilder : Builder<Episode.Bitlove> {

    /** Set the guid value. */
    public fun guid(guid: String): EpisodeBitloveBuilder

    override fun from(model: Episode.Bitlove?): EpisodeBitloveBuilder = whenNotNull(model) { bitlove ->
        guid(bitlove.guid)
    }
}
