package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.podlove.SimpleChapter
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingEpisodePodloveSimpleChapterBuilder : EpisodePodloveSimpleChapterBuilder {

    private var start: String? = null
    private var title: String? = null

    private var href: String? = null
    private var image: String? = null

    override fun start(start: String): EpisodePodloveSimpleChapterBuilder = apply { this.start = start }

    override fun title(title: String): EpisodePodloveSimpleChapterBuilder = apply { this.title = title }

    override fun href(href: String?): EpisodePodloveSimpleChapterBuilder = apply { this.href = href }

    override fun image(image: String?): EpisodePodloveSimpleChapterBuilder = apply { this.image = image }

    override val hasEnoughDataToBuild: Boolean
        get() = start != null && title != null

    override fun build(): SimpleChapter? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return SimpleChapter(
            start = checkRequiredProperty(::start),
            title = checkRequiredProperty(::title),
            href = href,
            image = image
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodePodloveSimpleChapterBuilder) return false

        if (start != other.start) return false
        if (title != other.title) return false
        if (href != other.href) return false
        if (image != other.image) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (href?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "ValidatingEpisodePodloveSimpleChapterBuilder(start='$start', title='$title', href=$href, image=$image)"
}
