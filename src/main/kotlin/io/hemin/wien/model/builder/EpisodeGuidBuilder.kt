package io.hemin.wien.model.builder

import io.hemin.wien.model.Episode
import io.hemin.wien.model.builder.Builder.Companion.anyNotNull

/** Builder class for [Episode.Guid] instances. */
class EpisodeGuidBuilder : Builder<Episode.Guid> {

    private var textContent: String?      = null
    private var isPermalink: Boolean? = null

    /** Set the textContent. */
    fun textContent(textContent: String?) = apply { this.textContent = textContent }

    /** Set the isPermalink. */
    fun isPermalink(isPermalink: Boolean?) = apply { this.isPermalink = isPermalink }

    override fun build(): Episode.Guid? {
        return if (anyNotNull(textContent, isPermalink))
            Episode.Guid(
                textContent = textContent,
                isPermalink = isPermalink
            )
        else null
    }


}
