package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.model.Episode

/** Builder class for [Episode.Podlove.SimpleChapter] instances. */
internal class ValidatingEpisodePodloveSimpleChapterBuilder : EpisodePodloveSimpleChapterBuilder {

    private lateinit var startValue: String
    private lateinit var titleValue: String

    private var href: String? = null
    private var image: String? = null

    /** Set the start value. */
    override fun start(start: String): EpisodePodloveSimpleChapterBuilder = apply { this.startValue = start }

    /** Set the title value. */
    override fun title(title: String): EpisodePodloveSimpleChapterBuilder = apply { this.titleValue = title }

    /** Set the href value. */
    override fun href(href: String?): EpisodePodloveSimpleChapterBuilder = apply { this.href = href }

    /** Set the image value. */
    override fun image(image: String?): EpisodePodloveSimpleChapterBuilder = apply { this.image = image }

    override fun build(): Episode.Podlove.SimpleChapter? {
        if (!::startValue.isInitialized || !::titleValue.isInitialized) {
            return null
        }

        return Episode.Podlove.SimpleChapter(
            start = startValue,
            title = titleValue,
            href = href,
            image = image
        )
    }
}
