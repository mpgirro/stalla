package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodePodloveBuilder : EpisodePodloveBuilder {

    private var simpleChapters: MutableList<Episode.Podlove.SimpleChapter> = mutableListOf()

    override fun addSimpleChapter(chapter: Episode.Podlove.SimpleChapter): EpisodePodloveBuilder = apply {
        simpleChapters.add(chapter)
    }

    override fun addSimpleChapters(chapters: List<Episode.Podlove.SimpleChapter>): EpisodePodloveBuilder = apply {
        simpleChapters.addAll(chapters)
    }

    override fun build(): Episode.Podlove? {
        if (simpleChapters.isEmpty()) {
            return null
        }

        return Episode.Podlove(immutableCopyOf(simpleChapters))
    }
}
