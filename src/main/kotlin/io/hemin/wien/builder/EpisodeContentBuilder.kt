package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Content] instances. */
class EpisodeContentBuilder : Builder<Episode.Content> {

    private var encoded: String? = null

    /** Set the encoded. */
    fun encoded(encoded: String?) = apply { this.encoded = encoded }

    override fun build(): Episode.Content? {
        return if (anyNotNull(encoded)) {
            Episode.Content(
                encoded = encoded
            )
        } else {
            null
        }

    }

}
