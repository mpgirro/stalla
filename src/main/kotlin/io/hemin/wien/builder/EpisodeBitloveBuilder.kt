package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Bitlove] instances. */
class EpisodeBitloveBuilder : Builder<Episode.Bitlove> {

    private lateinit var guidValue: String

    /** Set the guid value. */
    fun guid(guid: String) = apply { this.guidValue = guid }

    override fun build(): Episode.Bitlove {
        require(::guidValue.isInitialized) { "The episode bitlove guid is mandatory" }
        return Episode.Bitlove(guidValue)
    }
}
