package io.hemin.wien.builder

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image

class EpisodeItunesBuilder : Builder<Episode.Itunes> {

    private var title: String?       = null
    private var duration: String?    = null
    private var image: Image?        = null
    private var explicit: Boolean?   = null
    private var block: Boolean?      = null
    private var season: Int?         = null
    private var episode: Int?        = null
    private var episodeType: String? = null

    fun title(title: String?) = apply { this.title = title }

    fun duration(duration: String?) = apply { this.duration = duration }

    fun image(image: Image?) = apply { this.image = image }

    fun explicit(explicit: Boolean?) = apply { this.explicit = explicit }

    fun block(block: Boolean?) = apply { this.block = block }

    fun season(season: Int?) = apply { this.season = season }

    fun episode(episode: Int?) = apply { this.episode = episode }

    fun episodeType(episodeType: String?) = apply { this.episodeType = episodeType }

    override fun build(): Episode.Itunes? {
        return if (Builder.anyNotNull(title, duration, image, explicit, block, season, episode, episodeType))
            Episode.Itunes(
                title       = title,
                duration    = duration,
                image       = image,
                explicit    = explicit,
                block       = block,
                season      = season,
                episode     = episode,
                episodeType = episodeType
            )
        else
            null
    }

}
