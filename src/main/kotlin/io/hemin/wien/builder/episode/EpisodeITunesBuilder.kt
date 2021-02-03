package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.ITunes] instances. */
interface EpisodeITunesBuilder : Builder<Episode.ITunes> {

    /** Set the title value. */
    fun title(title: String?): EpisodeITunesBuilder

    /** Set the duration value. */
    fun duration(duration: String?): EpisodeITunesBuilder

    /** Set the Image builder. */
    fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeITunesBuilder

    /** Set the explicit flag value. */
    fun explicit(explicit: Boolean?): EpisodeITunesBuilder

    /** Set the block flag value. */
    fun block(block: Boolean): EpisodeITunesBuilder

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

    override fun from(model: Episode.ITunes?): EpisodeITunesBuilder = whenNotNull(model) { itunes ->
        title(itunes.title)
        duration(itunes.duration)
        imageBuilder(HrefOnlyImage.builder().from(itunes.image))
        explicit(itunes.explicit)
        block(itunes.block)
        season(itunes.season)
        episode(itunes.episode)
        episodeType(itunes.episodeType?.type)
        author(itunes.author)
        subtitle(itunes.subtitle)
        summary(itunes.summary)
    }
}
