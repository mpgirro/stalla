package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.MediaType
import dev.stalla.model.rss.Enclosure
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodeEnclosureBuilder : EpisodeEnclosureBuilder {

    private var url: String? = null
    private var length: Long? = null
    private var type: MediaType? = null

    override fun url(url: String): EpisodeEnclosureBuilder = apply { this.url = url }

    override fun length(length: Long): EpisodeEnclosureBuilder = apply { this.length = length }

    override fun type(type: MediaType): EpisodeEnclosureBuilder = apply { this.type = type }

    override val hasEnoughDataToBuild: Boolean
        get() = url != null && length != null && type != null

    override fun build(): Enclosure? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Enclosure(
            url = checkRequiredProperty(::url),
            length = checkRequiredProperty(::length),
            type = checkRequiredProperty(::type)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodeEnclosureBuilder) return false

        if (url != other.url) return false
        if (length != other.length) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + length.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

    override fun toString(): String = "ValidatingEpisodeEnclosureBuilder(url='$url', length=$length, type=$type)"
}
