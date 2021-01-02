package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Enclosure] instances. */
class EpisodeEnclosureBuilder : Builder<Episode.Enclosure> {

    private lateinit var urlValue: String
    private var lengthValue: Long = -1
    private lateinit var typeValue: String

    /** Set the url value. */
    fun url(url: String) = apply { this.urlValue = url }

    /** Set the length value. */
    fun length(length: Long) = apply { this.lengthValue = length }

    /** Set the type value. */
    fun type(type: String) = apply { this.typeValue = type }

    override fun build(): Episode.Enclosure {
        require(::urlValue.isInitialized) { "The enclosure URL is mandatory" }
        require(lengthValue > 0) { "The enclosure length must be greater than zero" }
        require(::typeValue.isInitialized) { "The enclosure type is mandatory" }

        return Episode.Enclosure(url = urlValue, length = lengthValue, type = typeValue)
    }
}
