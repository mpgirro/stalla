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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodePodloveBuilder) return false

        if (chapterBuilders != other.chapterBuilders) return false

        return true
    }

    override fun hashCode(): Int {
        return chapterBuilders.hashCode()
    }

    override fun toString() = "FakeEpisodePodloveBuilder(chapterBuilders=$chapterBuilders)"
}
