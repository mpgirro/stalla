package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodePodloveBuilder : FakeBuilder<Episode.Podlove>(), EpisodePodloveBuilder {

    var simpleChapters: MutableList<Episode.Podlove.SimpleChapter> = mutableListOf()

    /**
     * Adds a chapter to the list of chapters.
     *
     * @param chapter The chapter to add.
     */
    override fun addSimpleChapter(chapter: Episode.Podlove.SimpleChapter): EpisodePodloveBuilder = apply {
        simpleChapters.add(chapter)
    }

    /**
     * Adds chapters to the list of chapters.
     *
     * @param chapters The chapters to add.
     */
    override fun addSimpleChapters(chapters: List<Episode.Podlove.SimpleChapter>): EpisodePodloveBuilder = apply {
        simpleChapters.addAll(chapters)
    }
}
