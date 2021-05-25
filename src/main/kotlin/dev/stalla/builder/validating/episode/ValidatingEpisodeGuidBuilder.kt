package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.rss.Guid
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodeGuidBuilder : EpisodeGuidBuilder {

    private var text: String? = null

    private var isPermalink: Boolean? = null

    override fun textContent(textContent: String): EpisodeGuidBuilder = apply { this.text = textContent }

    override fun isPermalink(isPermalink: Boolean?): EpisodeGuidBuilder = apply { this.isPermalink = isPermalink }

    override val hasEnoughDataToBuild: Boolean
        get() = text != null

    override fun build(): Guid? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Guid(
            guid = checkRequiredProperty(::text, "guid text is missing"),
            isPermalink = isPermalink
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodeGuidBuilder) return false

        if (text != other.text) return false
        if (isPermalink != other.isPermalink) return false

        return true
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + (isPermalink?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "ValidatingEpisodeGuidBuilder(text='$text', isPermalink=$isPermalink)"
}
