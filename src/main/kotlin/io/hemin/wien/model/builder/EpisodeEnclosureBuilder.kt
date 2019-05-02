package io.hemin.wien.model.builder

import io.hemin.wien.model.Episode

class EpisodeEnclosureBuilder : Builder<Episode.Enclosure>() {

    private var url: String? = null
    private var length: Long? = null
    private var type: String? = null

    fun url(url: String?) = apply { this.url = url }

    fun length(length: Long?) = apply { this.length = length }

    fun type(type: String?) = apply { this.type = type }

    override fun build(): Episode.Enclosure? {
        return if (somePresent(url, length, type))
            Episode.Enclosure(
                url    = url,
                length = length,
                type   = type
            )
        else
            null
    }

}
