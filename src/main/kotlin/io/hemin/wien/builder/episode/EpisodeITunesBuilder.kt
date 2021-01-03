package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image

internal interface EpisodeITunesBuilder : Builder<Episode.ITunes> {

    /** Set the title value. */
    fun title(title: String?): EpisodeITunesBuilder

    /** Set the duration value. */
    fun duration(duration: String?): EpisodeITunesBuilder

    /** Set the Image. */
    fun image(image: Image?): EpisodeITunesBuilder

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
}
