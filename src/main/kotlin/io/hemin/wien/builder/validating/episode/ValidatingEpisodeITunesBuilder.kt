package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image

/** Builder class for [Episode.ITunes] instances. */
internal class ValidatingEpisodeITunesBuilder : EpisodeITunesBuilder {

    private var title: String? = null
    private var duration: String? = null
    private var image: Image? = null
    private var explicit: Boolean? = null
    private var block: Boolean? = null
    private var season: Int? = null
    private var episode: Int? = null
    private var episodeType: Episode.ITunes.EpisodeType? = null

    /** Set the title value. */
    override fun title(title: String?): EpisodeITunesBuilder = apply { this.title = title }

    /** Set the duration value. */
    override fun duration(duration: String?): EpisodeITunesBuilder = apply { this.duration = duration }

    /** Set the Image. */
    override fun image(image: Image?): EpisodeITunesBuilder = apply { this.image = image }

    /** Set the explicit flag value. */
    override fun explicit(explicit: Boolean?): EpisodeITunesBuilder = apply { this.explicit = explicit }

    /** Set the block flag value. */
    override fun block(block: Boolean?): EpisodeITunesBuilder = apply { this.block = block }

    /** Set the season value. */
    override fun season(season: Int?): EpisodeITunesBuilder = apply { this.season = season }

    /** Set the episode value. */
    override fun episode(episode: Int?): EpisodeITunesBuilder = apply { this.episode = episode }

    /** Set the episodeType value. */
    override fun episodeType(episodeType: String?): EpisodeITunesBuilder = apply {
        this.episodeType =
            Episode.ITunes.EpisodeType.of(episodeType)
    }

    override fun build(): Episode.ITunes? {
        if (allNull(title, duration, image, explicit, block, season, episode, episodeType)) {
            return null
        }

        return Episode.ITunes(
            title = title,
            duration = duration,
            image = image,
            explicit = explicit,
            block = block,
            season = season,
            episode = episode,
            episodeType = episodeType
        )
    }
}
