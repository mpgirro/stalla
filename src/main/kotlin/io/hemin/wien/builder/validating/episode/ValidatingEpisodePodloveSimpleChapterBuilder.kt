package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodePodloveSimpleChapterBuilder : EpisodePodloveSimpleChapterBuilder {

    private lateinit var startValue: String
    private lateinit var titleValue: String

    private var href: String? = null
    private var image: String? = null

    override fun start(start: String): EpisodePodloveSimpleChapterBuilder = apply { this.startValue = start }

    override fun title(title: String): EpisodePodloveSimpleChapterBuilder = apply { this.titleValue = title }

    override fun href(href: String?): EpisodePodloveSimpleChapterBuilder = apply { this.href = href }

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodePodloveSimpleChapterBuilder) return false

        if (startValue != other.startValue) return false
        if (titleValue != other.titleValue) return false
        if (href != other.href) return false
        if (image != other.image) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startValue.hashCode()
        result = 31 * result + titleValue.hashCode()
        result = 31 * result + (href?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        return result
    }
}
