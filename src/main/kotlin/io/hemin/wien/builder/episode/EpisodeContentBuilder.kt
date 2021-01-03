package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

internal interface EpisodeContentBuilder : Builder<Episode.Content> {

    /** Set the encoded value. */
    fun encoded(encoded: String): EpisodeContentBuilder
}
