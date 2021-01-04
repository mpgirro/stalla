package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.model.Episode

internal interface EpisodeITunesBuilder : Builder<Episode.ITunes> {

    /** Set the title value. */
    fun title(title: String?): EpisodeITunesBuilder

    /** Set the duration value. */
    fun duration(duration: String?): EpisodeITunesBuilder

    /** Set the Image builder. */
    fun imageBuilder(imageBuilder: ImageBuilder?): EpisodeITunesBuilder

    /** Set the explicit flag value. */
    fun explicit(explicit: Boolean?): EpisodeITunesBuilder

    /** Set the block flag value. */
    fun block(block: Boolean?): EpisodeITunesBuilder

    /** Set the season value. */
    fun season(season: Int?): EpisodeITunesBuilder

    /** Set the episode value. */
    fun episode(episode: Int?): EpisodeITunesBuilder

    /** Set the episodeType value. */
    fun episodeType(episodeType: String?): EpisodeITunesBuilder

    /** Set the author value. */
    fun author(author: String?): EpisodeITunesBuilder

    /** Set the subtitle value. */
    fun subtitle(subtitle: String?): EpisodeITunesBuilder

    /** Set the summary value. */
    fun summary(summary: String?): EpisodeITunesBuilder
}
