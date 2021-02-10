package dev.stalla.builder.validating.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeITunesBuilder
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.util.anyNotNull

internal class ValidatingEpisodeITunesBuilder : EpisodeITunesBuilder {

    private var title: String? = null
    private var duration: String? = null
    private var imageBuilder: HrefOnlyImageBuilder? = null
    private var explicit: Boolean? = null
    private var block: Boolean = false
    private var season: Int? = null
    private var episode: Int? = null
    private var episodeType: EpisodeType? = null
    private var author: String? = null
    private var subtitle: String? = null
    private var summary: String? = null

    override fun title(title: String?): EpisodeITunesBuilder = apply { this.title = title }

    override fun duration(duration: String?): EpisodeITunesBuilder = apply { this.duration = duration }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeITunesBuilder = apply { this.imageBuilder = imageBuilder }

    override fun explicit(explicit: Boolean?): EpisodeITunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeITunesBuilder = apply { this.block = block }

    override fun season(season: Int?): EpisodeITunesBuilder = apply { this.season = season }

    override fun episode(episode: Int?): EpisodeITunesBuilder = apply { this.episode = episode }

    override fun episodeType(episodeType: String?): EpisodeITunesBuilder = apply {
        this.episodeType = EpisodeType.from(episodeType)
    }

    override fun author(author: String?): EpisodeITunesBuilder = apply { this.author = author }

    override fun subtitle(subtitle: String?): EpisodeITunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): EpisodeITunesBuilder = apply { this.summary = summary }

    override val hasEnoughDataToBuild: Boolean
        get() = anyNotNull(title, duration, explicit, season, episode, episodeType, author, summary, subtitle) ||
            imageBuilder?.hasEnoughDataToBuild == true ||
            block

    override fun build(): EpisodeItunes? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val image = imageBuilder?.build()
        return EpisodeItunes(title, duration, image, explicit, block, season, episode, episodeType, author, subtitle, summary)
    }
}
