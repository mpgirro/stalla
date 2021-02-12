package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.podlove.SimpleChapter

internal class FakeEpisodePodloveSimpleChapterBuilder : FakeBuilder<SimpleChapter>(), EpisodePodloveSimpleChapterBuilder {

    var startValue: String? = null
    var titleValue: String? = null
    var href: String? = null
    var image: String? = null

    override fun start(start: String): EpisodePodloveSimpleChapterBuilder = apply { this.startValue = start }

    override fun title(title: String): EpisodePodloveSimpleChapterBuilder = apply { this.titleValue = title }

    override fun href(href: String?): EpisodePodloveSimpleChapterBuilder = apply { this.href = href }

    override fun image(image: String?): EpisodePodloveSimpleChapterBuilder = apply { this.image = image }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodePodloveSimpleChapterBuilder) return false

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

    override fun toString() =
        "FakeEpisodePodloveSimpleChapterBuilder(startValue='$startValue', titleValue='$titleValue', href=$href, image=$image)"
}
