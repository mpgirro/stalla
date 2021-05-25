package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodloveBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.model.podlove.EpisodePodlove
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingEpisodePodloveBuilder : EpisodePodloveBuilder {

    private var chapterBuilders: MutableList<EpisodePodloveSimpleChapterBuilder> = mutableListOf()

    override fun addSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveBuilder =
        apply { chapterBuilders.add(chapterBuilder) }

    override fun addAllSimpleChapterBuilders(
        chapterBuilders: List<EpisodePodloveSimpleChapterBuilder>
    ): EpisodePodloveBuilder = apply {
        this.chapterBuilders.addAll(chapterBuilders)
    }

    override val hasEnoughDataToBuild: Boolean
        get() = chapterBuilders.any { it.hasEnoughDataToBuild }

    override fun build(): EpisodePodlove? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return EpisodePodlove(
            simpleChapters = chapterBuilders.mapNotNull { it.build() }.asUnmodifiable()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodePodloveBuilder) return false

        if (chapterBuilders != other.chapterBuilders) return false

        return true
    }

    override fun hashCode(): Int = chapterBuilders.hashCode()

    override fun toString(): String = "ValidatingEpisodePodloveBuilder(chapterBuilders=$chapterBuilders)"
}
