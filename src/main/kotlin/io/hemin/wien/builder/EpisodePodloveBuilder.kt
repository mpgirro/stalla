package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Podlove] instances. */
class EpisodePodloveBuilder : Builder<Episode.Podlove> {

    private var simpleChapters: MutableList<Episode.Podlove.SimpleChapter> = mutableListOf()

    /**
     * Adds a chapter to the list of chapters.
     *
     * @param chapter The chapter to add.
     */
    fun addSimpleChapter(chapter: Episode.Podlove.SimpleChapter?) {
        chapter?.let { this.simpleChapters.add(it) }
    }

    /**
     * Adds chapters to the list of chapters.
     *
     * @param chapters The chapters to add.
     */
    fun addSimpleChapters(chapters: List<Episode.Podlove.SimpleChapter>?) {
        chapters?.forEach(::addSimpleChapter)
    }

    override fun build(): Episode.Podlove? {
        val oSimpleChapters = if (simpleChapters.isEmpty()) null else Object()
        return if (anyNotNull(oSimpleChapters)) {
            Episode.Podlove(
                simpleChapters = immutableCopyOf(simpleChapters)
            )
        } else {
            null
        }
    }

}
