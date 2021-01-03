package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodePodloveSimpleChapterBuilder : FakeBuilder<Episode.Podlove.SimpleChapter>(), EpisodePodloveSimpleChapterBuilder {

    lateinit var startValue: String
    lateinit var titleValue: String

    var href: String? = null
    var image: String? = null

    override fun start(start: String): EpisodePodloveSimpleChapterBuilder = apply { this.startValue = start }

    override fun title(title: String): EpisodePodloveSimpleChapterBuilder = apply { this.titleValue = title }

    override fun href(href: String?): EpisodePodloveSimpleChapterBuilder = apply { this.href = href }

    override fun image(image: String?): EpisodePodloveSimpleChapterBuilder = apply { this.image = image }
}
