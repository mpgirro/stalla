package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podlove.EpisodePodlove
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [EpisodePodlove] instances. */
public interface EpisodePodloveBuilder : Builder<EpisodePodlove> {

    /** Adds a [EpisodePodloveSimpleChapterBuilder] to the list of chapter builders. */
    public fun addSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveBuilder

    /** Adds multiple [EpisodePodloveSimpleChapterBuilder] to the list of chapter builders. */
    public fun addSimpleChapterBuilders(chapterBuilders: List<EpisodePodloveSimpleChapterBuilder>): EpisodePodloveBuilder

    override fun from(model: EpisodePodlove?): EpisodePodloveBuilder = whenNotNull(model) { podlove ->
        addSimpleChapterBuilders(podlove.simpleChapters.asBuilders())
    }
}
