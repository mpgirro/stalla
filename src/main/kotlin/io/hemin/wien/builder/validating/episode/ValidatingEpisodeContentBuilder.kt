package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodeContentBuilder : EpisodeContentBuilder {

    private lateinit var encodedValue: String

    override fun encoded(encoded: String): EpisodeContentBuilder = apply { this.encodedValue = encoded }

    override val hasEnoughDataToBuild: Boolean
        get() = ::encodedValue.isInitialized

    override fun build(): Episode.Content? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode.Content(encodedValue)
    }
}
