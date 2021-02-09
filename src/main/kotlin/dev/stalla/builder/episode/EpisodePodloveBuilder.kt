package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Episode
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [Episode.Podlove] instances. */
public interface EpisodePodloveBuilder : Builder<Episode.Podlove> {

    /** Adds a [EpisodePodloveSimpleChapterBuilder] to the list of chapter builders. */
    public fun addSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveBuilder

    /** Adds multiple [EpisodePodloveSimpleChapterBuilder] to the list of chapter builders. */
    public fun addSimpleChapterBuilders(chapterBuilders: List<EpisodePodloveSimpleChapterBuilder>): EpisodePodloveBuilder

    override fun from(model: Episode.Podlove?): EpisodePodloveBuilder = whenNotNull(model) { podlove ->
        addSimpleChapterBuilders(podlove.simpleChapters.asBuilders())
    }
}