package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podlove.EpisodePodlove
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [EpisodePodlove] instances. */
public interface EpisodePodloveBuilder : Builder<EpisodePodlove> {

    /** Adds the [EpisodePodloveSimpleChapterBuilder] to the list of chapter builders. */
    public fun addSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveBuilder

    /** Adds all of the [EpisodePodloveSimpleChapterBuilder] to the list of chapter builders. */
    public fun addAllSimpleChapterBuilder(chapterBuilders: List<EpisodePodloveSimpleChapterBuilder>): EpisodePodloveBuilder

    override fun applyFrom(prototype: EpisodePodlove?): EpisodePodloveBuilder = whenNotNull(prototype) { podlove ->
        addAllSimpleChapterBuilder(podlove.simpleChapters.asBuilders())
    }
}
