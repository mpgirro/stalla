package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeContentBuilder
import dev.stalla.model.content.Content
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingEpisodeContentBuilder : EpisodeContentBuilder {

    private lateinit var encodedValue: String

    override fun encoded(encoded: String): EpisodeContentBuilder = apply { this.encodedValue = encoded }

    override val hasEnoughDataToBuild: Boolean
        get() = ::encodedValue.isInitialized

    override fun build(): Content? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Content(encodedValue)
    }
}
