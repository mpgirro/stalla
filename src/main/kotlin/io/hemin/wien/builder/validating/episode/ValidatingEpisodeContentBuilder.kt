package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.model.Episode

/** Builder class for [Episode.Content] instances. */
internal class ValidatingEpisodeContentBuilder : EpisodeContentBuilder {

    private lateinit var encodedValue: String

    /** Set the encoded value. */
    override fun encoded(encoded: String): EpisodeContentBuilder = apply { this.encodedValue = encoded }

    override fun build(): Episode.Content? {
        if (!::encodedValue.isInitialized) {
            return null
        }

        return Episode.Content(encodedValue)
    }
}
