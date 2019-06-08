package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Bitlove] instances. */
class EpisodeBitloveBuilder : Builder<Episode.Bitlove> {

    private var guid: String? = null

    /** Set the guid value. */
    fun guid(guid: String?) = apply { this.guid = guid }

    override fun build(): Episode.Bitlove? {
        return if (anyNotNull(guid)) {
            Episode.Bitlove(
                guid = guid
            )
        } else {
            null
        }
    }

}
