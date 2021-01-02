package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Content] instances. */
class EpisodeContentBuilder : Builder<Episode.Content> {

    private lateinit var encodedValue: String

    /** Set the encoded value. */
    fun encoded(encoded: String) = apply { this.encodedValue = encoded }

    override fun build(): Episode.Content {
        require(::encodedValue.isInitialized) { "The episode content:encoded is mandatory" }

        return Episode.Content(encodedValue)
    }
}
