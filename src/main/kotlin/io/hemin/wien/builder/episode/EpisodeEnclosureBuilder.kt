package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

internal interface EpisodeEnclosureBuilder : Builder<Episode.Enclosure> {

    /** Set the url value. */
    fun url(url: String): EpisodeEnclosureBuilder

    /** Set the length value. */
    fun length(length: Long): EpisodeEnclosureBuilder

    /** Set the type value. */
    fun type(type: String): EpisodeEnclosureBuilder
}
