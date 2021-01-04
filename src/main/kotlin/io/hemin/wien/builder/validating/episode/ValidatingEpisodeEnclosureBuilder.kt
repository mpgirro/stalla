package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodeEnclosureBuilder : EpisodeEnclosureBuilder {

    private lateinit var urlValue: String
    private var lengthValue: Long = -1
    private lateinit var typeValue: String

    override fun url(url: String): EpisodeEnclosureBuilder = apply { this.urlValue = url }

    override fun length(length: Long): EpisodeEnclosureBuilder = apply { this.lengthValue = length }

    override fun type(type: String): EpisodeEnclosureBuilder = apply { this.typeValue = type }

    override fun build(): Episode.Enclosure? {
        if (!::urlValue.isInitialized || lengthValue <= 0 || !::typeValue.isInitialized) {
            return null
        }

        return Episode.Enclosure(url = urlValue, length = lengthValue, type = typeValue)
    }
}