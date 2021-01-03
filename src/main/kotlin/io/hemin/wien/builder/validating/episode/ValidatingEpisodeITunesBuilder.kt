package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodeITunesBuilder : EpisodeITunesBuilder {

    private var title: String? = null
    private var duration: String? = null
    private var imageBuilder: ImageBuilder? = null
    private var explicit: Boolean? = null
    private var block: Boolean? = null
    private var season: Int? = null
    private var episode: Int? = null
    private var episodeType: Episode.ITunes.EpisodeType? = null

    override fun title(title: String?): EpisodeITunesBuilder = apply { this.title = title }

    override fun duration(duration: String?): EpisodeITunesBuilder = apply { this.duration = duration }

    override fun imageBuilder(imageBuilder: ImageBuilder?): EpisodeITunesBuilder = apply { this.imageBuilder = imageBuilder }

    override fun explicit(explicit: Boolean?): EpisodeITunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): EpisodeITunesBuilder = apply { this.block = block }

    override fun season(season: Int?): EpisodeITunesBuilder = apply { this.season = season }

    override fun episode(episode: Int?): EpisodeITunesBuilder = apply { this.episode = episode }

    override fun episodeType(episodeType: String?): EpisodeITunesBuilder = apply {
        this.episodeType =
            Episode.ITunes.EpisodeType.of(episodeType)
    }

    override fun build(): Episode.ITunes? {
        val image = imageBuilder?.build()
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
