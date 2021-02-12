package dev.stalla.builder.validating.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeItunesBuilder2
import dev.stalla.model.itunes.EpisodeItunes2
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.util.anyNotNull

internal class ValidatingEpisodeItunesBuilder2 : EpisodeItunesBuilder2 {

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

    override fun title(title: String?): EpisodeItunesBuilder2 = apply { this.title = title }

    override fun duration(duration: String?): EpisodeItunesBuilder2 = apply { this.duration = duration }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeItunesBuilder2 = apply { this.imageBuilder = imageBuilder }

    override fun explicit(explicit: Boolean?): EpisodeItunesBuilder2 = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeItunesBuilder2 = apply { this.block = block }

    override fun season(season: Int?): EpisodeItunesBuilder2 = apply { this.season = season }

    override fun episode(episode: Int?): EpisodeItunesBuilder2 = apply { this.episode = episode }

    override fun episodeType(episodeType: String?): EpisodeItunesBuilder2 = apply {
        this.episodeType = EpisodeType.from(episodeType)
    }

    override fun author(author: String?): EpisodeItunesBuilder2 = apply { this.author = author }

    override fun subtitle(subtitle: String?): EpisodeItunesBuilder2 = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): EpisodeItunesBuilder2 = apply { this.summary = summary }

    override val hasEnoughDataToBuild: Boolean
        get() = anyNotNull(title, duration, explicit, season, episode, episodeType, author, summary, subtitle) ||
            imageBuilder?.hasEnoughDataToBuild == true ||
            block

    override fun build(): EpisodeItunes2? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val image = imageBuilder?.build()
        return EpisodeItunes2(title, duration, image, explicit, block, season, episode, episodeType, author, subtitle, summary)
    }
}
