package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

/** Builder for constructing [Episode.Bitlove] instances. */
interface EpisodeBitloveBuilder : Builder<Episode.Bitlove> {

    /** Set the guid value. */
    fun guid(guid: String): EpisodeBitloveBuilder
}
