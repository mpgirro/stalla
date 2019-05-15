package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Podlove.SimpleChapter] instances. */
class EpisodePodloveSimpleChapterBuilder : Builder<Episode.Podlove.SimpleChapter> {

    private var start: String? = null
    private var title: String? = null
    private var href: String?  = null
    private var image: String? = null

    /** Set the start value. */
    fun start(start: String?) = apply { this.start = start }

    /** Set the title value. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the href value. */
    fun href(href: String?) = apply { this.href = href }

    /** Set the image value. */
    fun image(image: String?) = apply { this.image = image }

    override fun build(): Episode.Podlove.SimpleChapter? {
        return if (anyNotNull(start, title, href, image)) {
            Episode.Podlove.SimpleChapter(
                start = start,
                title = title,
                href  = href,
                image = image
            )
        } else {
            null
        }
    }

}
