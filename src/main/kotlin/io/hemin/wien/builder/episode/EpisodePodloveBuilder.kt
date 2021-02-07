package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.asBuilders
import io.hemin.wien.util.whenNotNull

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
