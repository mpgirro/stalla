package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.bitlove.Bitlove
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodeBitloveBuilder : EpisodeBitloveBuilder {

    private var guid: String? = null

    override fun guid(guid: String): EpisodeBitloveBuilder = apply { this.guid = guid }

    override val hasEnoughDataToBuild: Boolean
        get() = guid != null

    override fun build(): Bitlove? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Bitlove(guid = checkRequiredProperty(::guid))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodeBitloveBuilder) return false

        if (guid != other.guid) return false

        return true
    }

    override fun hashCode(): Int = guid.hashCode()

    override fun toString(): String = "ValidatingEpisodeBitloveBuilder(guid='$guid')"
}
