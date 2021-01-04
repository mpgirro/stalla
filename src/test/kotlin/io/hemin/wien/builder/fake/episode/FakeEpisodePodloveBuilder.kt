package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodePodloveBuilder : FakeBuilder<Episode.Podlove>(), EpisodePodloveBuilder {

    var chapterBuilders: MutableList<EpisodePodloveSimpleChapterBuilder> = mutableListOf()

    override fun addSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveBuilder = apply {
        chapterBuilders.add(chapterBuilder)
    }

    override fun addSimpleChapterBuilders(chapterBuilders: List<EpisodePodloveSimpleChapterBuilder>): EpisodePodloveBuilder = apply {
        this.chapterBuilders.addAll(chapterBuilders)
    }
}
