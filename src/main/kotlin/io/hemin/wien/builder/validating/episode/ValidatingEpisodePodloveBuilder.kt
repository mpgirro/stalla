package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodePodloveBuilder : EpisodePodloveBuilder {

    private var chapterBuilders: MutableList<EpisodePodloveSimpleChapterBuilder> = mutableListOf()

    override fun addSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveBuilder = apply {
        chapterBuilders.add(chapterBuilder)
    }

    override fun addSimpleChapterBuilders(chapterBuilders: List<EpisodePodloveSimpleChapterBuilder>): EpisodePodloveBuilder = apply {
        this.chapterBuilders.addAll(chapterBuilders)
    }

    override fun build(): Episode.Podlove? {
        val chapters = chapterBuilders.mapNotNull { it.build() }
        if (chapters.isEmpty()) {
            return null
        }

        return Episode.Podlove(chapters)
    }
}
