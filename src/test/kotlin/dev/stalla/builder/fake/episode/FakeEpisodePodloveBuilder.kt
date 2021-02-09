package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodloveBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.Episode

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
