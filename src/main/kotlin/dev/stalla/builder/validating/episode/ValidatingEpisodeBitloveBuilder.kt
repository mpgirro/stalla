package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.model.Episode

internal class ValidatingEpisodeBitloveBuilder : EpisodeBitloveBuilder {

    private lateinit var guidValue: String

    override fun guid(guid: String): EpisodeBitloveBuilder = apply { this.guidValue = guid }

    override val hasEnoughDataToBuild: Boolean
        get() = ::guidValue.isInitialized

    override fun build(): Episode.Bitlove? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode.Bitlove(guidValue)
    }
}
