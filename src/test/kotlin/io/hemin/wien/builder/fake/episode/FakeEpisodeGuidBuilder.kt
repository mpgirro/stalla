package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeGuidBuilder : FakeBuilder<Episode.Guid>(), EpisodeGuidBuilder {

    var textContent: String? = null
    var isPermalink: Boolean? = null

    /** Set the textContent value. */
    override fun textContent(textContent: String?): EpisodeGuidBuilder = apply { this.textContent = textContent }

    /** Set the isPermalink value. */
    override fun isPermalink(isPermalink: Boolean?): EpisodeGuidBuilder = apply { this.isPermalink = isPermalink }
}
