package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeContentBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.content.Content
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodeContentBuilder : EpisodeContentBuilder {

    private var encoded: String? = null

    override fun encoded(encoded: String): EpisodeContentBuilder = apply { this.encoded = encoded }

    override val hasEnoughDataToBuild: Boolean
        get() = encoded != null

    override fun build(): Content? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Content(encoded = checkRequiredProperty(::encoded))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodeContentBuilder) return false

        if (encoded != other.encoded) return false

        return true
    }

    override fun hashCode(): Int = encoded.hashCode()

    override fun toString(): String = "ValidatingEpisodeContentBuilder(encodedValue='$encoded')"
}
