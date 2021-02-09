package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.model.Episode

internal class ValidatingEpisodeEnclosureBuilder : EpisodeEnclosureBuilder {

    private lateinit var urlValue: String
    private var lengthValue: Long = -1
    private lateinit var typeValue: String

    override fun url(url: String): EpisodeEnclosureBuilder = apply { this.urlValue = url }

    override fun length(length: Long): EpisodeEnclosureBuilder = apply { this.lengthValue = length }

    override fun type(type: String): EpisodeEnclosureBuilder = apply { this.typeValue = type }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && lengthValue >= 0 && ::typeValue.isInitialized

    override fun build(): Episode.Enclosure? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode.Enclosure(urlValue, lengthValue, typeValue)
    }
}
