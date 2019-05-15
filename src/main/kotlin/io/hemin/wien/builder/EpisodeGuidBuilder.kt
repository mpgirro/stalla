package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Guid] instances. */
class EpisodeGuidBuilder : Builder<Episode.Guid> {

    private var textContent: String?      = null
    private var isPermalink: Boolean? = null

    /** Set the textContent value. */
    fun textContent(textContent: String?) = apply { this.textContent = textContent }

    /** Set the isPermalink value. */
    fun isPermalink(isPermalink: Boolean?) = apply { this.isPermalink = isPermalink }

    override fun build(): Episode.Guid? {
        return if (anyNotNull(textContent, isPermalink)) {
            Episode.Guid(
                textContent = textContent,
                isPermalink = isPermalink
            )
        } else {
            null
        }
    }


}
