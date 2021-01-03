package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeBitloveBuilder
import io.hemin.wien.model.Episode

/** Builder class for [Episode.Bitlove] instances. */
internal class ValidatingEpisodeBitloveBuilder : EpisodeBitloveBuilder {

    private var guid: String? = null

    /** Set the guid value. */
    override fun guid(guid: String): EpisodeBitloveBuilder = apply { this.guid = guid }

    override fun build(): Episode.Bitlove? {
        val guidValue = guid ?: return null
        return Episode.Bitlove(guidValue)
    }
}
