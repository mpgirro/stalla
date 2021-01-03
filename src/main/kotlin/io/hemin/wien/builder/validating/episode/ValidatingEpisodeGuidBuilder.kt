package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.model.Episode

/** Builder class for [Episode.Guid] instances. */
internal class ValidatingEpisodeGuidBuilder : EpisodeGuidBuilder {

    private var textContent: String? = null
    private var isPermalink: Boolean? = null

    /** Set the textContent value. */
    override fun textContent(textContent: String?): EpisodeGuidBuilder = apply { this.textContent = textContent }

    /** Set the isPermalink value. */
    override fun isPermalink(isPermalink: Boolean?): EpisodeGuidBuilder = apply { this.isPermalink = isPermalink }

    override fun build(): Episode.Guid? {
        if (allNull(textContent, isPermalink)) {
            return null
        }

        return Episode.Guid(textContent, isPermalink)
    }
}
