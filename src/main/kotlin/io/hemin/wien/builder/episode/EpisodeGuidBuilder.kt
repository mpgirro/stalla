package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

internal interface EpisodeGuidBuilder : Builder<Episode.Guid> {

    /** Set the textContent value. */
    fun textContent(textContent: String?): EpisodeGuidBuilder

    /** Set the isPermalink value. */
    fun isPermalink(isPermalink: Boolean?): EpisodeGuidBuilder
}
