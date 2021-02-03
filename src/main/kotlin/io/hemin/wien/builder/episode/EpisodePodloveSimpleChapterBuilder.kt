package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.Podlove.SimpleChapter] instances. */
interface EpisodePodloveSimpleChapterBuilder : Builder<Episode.Podlove.SimpleChapter> {

    /** Set the start value. */
    fun start(start: String): EpisodePodloveSimpleChapterBuilder

    /** Set the title value. */
    fun title(title: String): EpisodePodloveSimpleChapterBuilder

    /** Set the href value. */
    fun href(href: String?): EpisodePodloveSimpleChapterBuilder

    /** Set the image value. */
    fun image(image: String?): EpisodePodloveSimpleChapterBuilder

    override fun from(model: Episode.Podlove.SimpleChapter?): EpisodePodloveSimpleChapterBuilder = whenNotNull(model) { simpleChapter ->
        start(simpleChapter.start)
        title(simpleChapter.title)
        href(simpleChapter.href)
        image(simpleChapter.image)
    }
}
