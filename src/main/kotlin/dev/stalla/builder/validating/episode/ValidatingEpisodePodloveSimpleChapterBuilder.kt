package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.model.podlove.SimpleChapter
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingEpisodePodloveSimpleChapterBuilder : EpisodePodloveSimpleChapterBuilder {

    private lateinit var startValue: String
    private lateinit var titleValue: String

    private var href: String? = null
    private var image: String? = null

    override fun start(start: String): EpisodePodloveSimpleChapterBuilder = apply { this.startValue = start }

    override fun title(title: String): EpisodePodloveSimpleChapterBuilder = apply { this.titleValue = title }

    override fun href(href: String?): EpisodePodloveSimpleChapterBuilder = apply { this.href = href }

    override fun image(image: String?): EpisodePodloveSimpleChapterBuilder = apply { this.image = image }

    override val hasEnoughDataToBuild: Boolean
        get() = ::startValue.isInitialized && ::titleValue.isInitialized

    override fun build(): SimpleChapter? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return SimpleChapter(
            start = startValue,
            title = titleValue,
            href = href,
            image = image
        )
    }
}
