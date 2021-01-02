package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Podlove.SimpleChapter] instances. */
class EpisodePodloveSimpleChapterBuilder : Builder<Episode.Podlove.SimpleChapter> {

    private lateinit var startValue: String
    private lateinit var titleValue: String

    private var href: String? = null
    private var image: String? = null

    /** Set the start value. */
    fun start(start: String) = apply { this.startValue = start }

    /** Set the title value. */
    fun title(title: String) = apply { this.titleValue = title }

    /** Set the href value. */
    fun href(href: String?) = apply { this.href = href }

    /** Set the image value. */
    fun image(image: String?) = apply { this.image = image }

    override fun build(): Episode.Podlove.SimpleChapter {
        require(::startValue.isInitialized) { "The chapter start value is mandatory" }
        require(::titleValue.isInitialized) { "The chapter title is mandatory" }

        return Episode.Podlove.SimpleChapter(
            start = startValue,
            title = titleValue,
            href = href,
            image = image
        )
    }
}
