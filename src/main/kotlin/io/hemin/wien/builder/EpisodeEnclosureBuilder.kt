package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Enclosure] instances. */
class EpisodeEnclosureBuilder : Builder<Episode.Enclosure> {

    private var url: String? = null
    private var length: Long? = null
    private var type: String? = null

    /** Set the url value. */
    fun url(url: String?) = apply { this.url = url }

    /** Set the length value. */
    fun length(length: Long?) = apply { this.length = length }

    /** Set the type value. */
    fun type(type: String?) = apply { this.type = type }

    override fun build(): Episode.Enclosure? {
        return if (anyNotNull(url, length, type)) {
            Episode.Enclosure(
                url = url,
                length = length,
                type = type
            )
        } else {
            null
        }
    }
}
