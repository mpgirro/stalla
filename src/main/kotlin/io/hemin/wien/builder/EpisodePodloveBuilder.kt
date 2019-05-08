package io.hemin.wien.builder

import io.hemin.wien.model.Episode

class EpisodePodloveBuilder : Builder<Episode.Podlove> {

    private var simpleChapters: MutableList<Episode.Podlove.SimpleChapter> = mutableListOf()

    fun addSimpleChapter(chapter: Episode.Podlove.SimpleChapter?) {
        chapter?.let { this.simpleChapters.add(it) }
    }

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
