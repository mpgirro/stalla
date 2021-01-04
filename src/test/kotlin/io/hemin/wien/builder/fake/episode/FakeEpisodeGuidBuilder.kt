package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeGuidBuilder : FakeBuilder<Episode.Guid>(), EpisodeGuidBuilder {

    var textContent: String? = null
    var isPermalink: Boolean? = null

    override fun textContent(textContent: String): EpisodeGuidBuilder = apply { this.textContent = textContent }

    override fun isPermalink(isPermalink: Boolean?): EpisodeGuidBuilder = apply { this.isPermalink = isPermalink }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeGuidBuilder) return false

        if (textContent != other.textContent) return false
        if (isPermalink != other.isPermalink) return false

        return true
    }

    override fun hashCode(): Int {
        var result = textContent?.hashCode() ?: 0
        result = 31 * result + (isPermalink?.hashCode() ?: 0)
        return result
    }
}
