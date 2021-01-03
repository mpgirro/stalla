package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

internal interface EpisodeBitloveBuilder : Builder<Episode.Bitlove> {

    /** Set the guid value. */
    fun guid(guid: String): EpisodeBitloveBuilder
}
