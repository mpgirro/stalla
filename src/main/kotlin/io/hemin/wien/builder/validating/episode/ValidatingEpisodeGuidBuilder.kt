package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodeGuidBuilder : EpisodeGuidBuilder {

    private lateinit var text: String

    private var isPermalink: Boolean? = null

    override fun textContent(textContent: String): EpisodeGuidBuilder = apply { this.text = textContent }

    override fun isPermalink(isPermalink: Boolean?): EpisodeGuidBuilder = apply { this.isPermalink = isPermalink }

    override val hasEnoughDataToBuild: Boolean
        get() = ::text.isInitialized

    override fun build(): Episode.Guid? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode.Guid(text, isPermalink)
    }
}
