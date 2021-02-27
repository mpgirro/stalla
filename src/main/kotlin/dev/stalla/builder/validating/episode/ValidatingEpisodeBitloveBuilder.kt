package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.model.bitlove.Bitlove
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingEpisodeBitloveBuilder : EpisodeBitloveBuilder {

    private lateinit var guidValue: String

    override fun guid(guid: String): EpisodeBitloveBuilder = apply { this.guidValue = guid }

    override val hasEnoughDataToBuild: Boolean
        get() = ::guidValue.isInitialized

    override fun build(): Bitlove? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Bitlove(guid = guidValue)
    }
}
