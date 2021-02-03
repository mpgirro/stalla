package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.Enclosure] instances. */
interface EpisodeEnclosureBuilder : Builder<Episode.Enclosure> {

    /** Set the url value. */
    fun url(url: String): EpisodeEnclosureBuilder

    /** Set the length value. */
    fun length(length: Long): EpisodeEnclosureBuilder

    /** Set the type value. */
    fun type(type: String): EpisodeEnclosureBuilder

    override fun from(model: Episode.Enclosure?): EpisodeEnclosureBuilder = whenNotNull(model) { enclosure ->
        url(enclosure.url)
        length(enclosure.length)
        type(enclosure.type)
    }
}
