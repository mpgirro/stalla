package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

internal interface EpisodePodloveSimpleChapterBuilder : Builder<Episode.Podlove.SimpleChapter> {

    /** Set the start value. */
    fun start(start: String): EpisodePodloveSimpleChapterBuilder

    /** Set the title value. */
    fun title(title: String): EpisodePodloveSimpleChapterBuilder

    /** Set the href value. */
    fun href(href: String?): EpisodePodloveSimpleChapterBuilder

    /** Set the image value. */
    fun image(image: String?): EpisodePodloveSimpleChapterBuilder
}
