package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

internal interface EpisodePodloveBuilder : Builder<Episode.Podlove> {

    /**
     * Adds a chapter to the list of chapters.
     *
     * @param chapter The chapter to add.
     */
    fun addSimpleChapter(chapter: Episode.Podlove.SimpleChapter): EpisodePodloveBuilder

    /**
     * Adds chapters to the list of chapters.
     *
     * @param chapters The chapters to add.
     */
    fun addSimpleChapters(chapters: List<Episode.Podlove.SimpleChapter>): EpisodePodloveBuilder
}
