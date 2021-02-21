package dev.stalla.builder.validating.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeItunesBuilder
import dev.stalla.model.StyledDuration
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.util.InternalApi
import dev.stalla.util.anyNotNull

@InternalApi
internal class ValidatingEpisodeItunesBuilder : EpisodeItunesBuilder {

    private var title: String? = null
    private var duration: StyledDuration? = null
    private var imageBuilder: HrefOnlyImageBuilder? = null
    private var explicit: Boolean? = null
    private var block: Boolean = false
    private var season: Int? = null
    private var episode: Int? = null
    private var episodeType: EpisodeType? = null
    private var author: String? = null
    private var subtitle: String? = null
    private var summary: String? = null

    override fun title(title: String?): EpisodeItunesBuilder = apply { this.title = title }

    override fun duration(duration: StyledDuration?): EpisodeItunesBuilder = apply { this.duration = duration }

    override fun imageBuilder(
        imageBuilder: HrefOnlyImageBuilder?
    ): EpisodeItunesBuilder = apply { this.imageBuilder = imageBuilder }

    override fun explicit(explicit: Boolean?): EpisodeItunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeItunesBuilder = apply { this.block = block }

    override fun season(season: Int?): EpisodeItunesBuilder = apply { this.season = season }

    override fun episode(episode: Int?): EpisodeItunesBuilder = apply { this.episode = episode }

    override fun episodeType(episodeType: String?): EpisodeItunesBuilder = apply {
        this.episodeType = EpisodeType.of(episodeType)
    }

    override fun author(author: String?): EpisodeItunesBuilder = apply { this.author = author }

    override fun subtitle(subtitle: String?): EpisodeItunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): EpisodeItunesBuilder = apply { this.summary = summary }

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
