package io.hemin.wien.builder

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Episode.Itunes.EpisodeType
import io.hemin.wien.model.Image

/** Builder class for [Episode.Itunes] instances. */
class EpisodeItunesBuilder : Builder<Episode.Itunes> {

    private var title: String?            = null
    private var duration: String?         = null
    private var image: Image?             = null
    private var explicit: Boolean?        = null
    private var block: Boolean?           = null
    private var season: Int?              = null
    private var episode: Int?             = null
    private var episodeType: EpisodeType? = null

    /** Set the title. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the duration. */
    fun duration(duration: String?) = apply { this.duration = duration }

    /** Set the Image. */
    fun image(image: Image?) = apply { this.image = image }

    /** Set the explicit flag. */
    fun explicit(explicit: Boolean?) = apply { this.explicit = explicit }

    /** Set the block flag. */
    fun block(block: Boolean?) = apply { this.block = block }

    /** Set the season. */
    fun season(season: Int?) = apply { this.season = season }

    /** Set the episode. */
    fun episode(episode: Int?) = apply { this.episode = episode }

    /** Set the episodeType. */
    fun episodeType(episodeType: String?) = apply { this.episodeType = EpisodeType.of(episodeType) }

    override fun build(): Episode.Itunes? {
        return if (anyNotNull(title, duration, image, explicit, block, season, episode, episodeType)) {
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
        } else {
            null
        }
    }

}
