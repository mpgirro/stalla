package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodloveBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.model.Episode

internal class ValidatingEpisodePodloveBuilder : EpisodePodloveBuilder {

    private var chapterBuilders: MutableList<EpisodePodloveSimpleChapterBuilder> = mutableListOf()

    override fun addSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveBuilder = apply {
        chapterBuilders.add(chapterBuilder)
    }

    override fun addSimpleChapterBuilders(chapterBuilders: List<EpisodePodloveSimpleChapterBuilder>): EpisodePodloveBuilder = apply {
        this.chapterBuilders.addAll(chapterBuilders)
    }

    override val hasEnoughDataToBuild: Boolean
        get() = chapterBuilders.any { it.hasEnoughDataToBuild }

    override fun build(): Episode.Podlove? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val chapters = chapterBuilders.mapNotNull { it.build() }
        return Episode.Podlove(chapters)
    }
}