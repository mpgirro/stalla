package dev.stalla.builder.fake.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeItunesBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.StyledDuration
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.itunes.EpisodeType

internal class FakeEpisodeItunesBuilder : FakeBuilder<EpisodeItunes>(), EpisodeItunesBuilder {

    var title: String? = null
    var duration: StyledDuration? = null
    var imageBuilder: HrefOnlyImageBuilder? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var season: Int? = null
    var episode: Int? = null
    var episodeType: EpisodeType? = null
    var author: String? = null
    var subtitle: String? = null
    var summary: String? = null

    override fun title(title: String?): EpisodeItunesBuilder = apply { this.title = title }

    override fun duration(duration: StyledDuration?): EpisodeItunesBuilder = apply { this.duration = duration }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeItunesBuilder = apply { this.imageBuilder = imageBuilder }

    override fun explicit(explicit: Boolean?): EpisodeItunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeItunesBuilder = apply { this.block = block }

    override fun season(season: Int?): EpisodeItunesBuilder = apply { this.season = season }

    override fun episode(episode: Int?): EpisodeItunesBuilder = apply { this.episode = episode }

    override fun episodeType(episodeType: String?): EpisodeItunesBuilder = apply { this.episodeType = EpisodeType.of(episodeType) }

    override fun author(author: String?): EpisodeItunesBuilder = apply { this.author = author }

    override fun subtitle(subtitle: String?): EpisodeItunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): EpisodeItunesBuilder = apply { this.summary = summary }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeItunesBuilder) return false

        if (title != other.title) return false
        if (duration != other.duration) return false
        if (imageBuilder != other.imageBuilder) return false
        if (explicit != other.explicit) return false
        if (block != other.block) return false
        if (season != other.season) return false
        if (episode != other.episode) return false
        if (episodeType != other.episodeType) return false
        if (author != other.author) return false
        if (subtitle != other.subtitle) return false
        if (summary != other.summary) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (duration?.hashCode() ?: 0)
        result = 31 * result + (imageBuilder?.hashCode() ?: 0)
        result = 31 * result + (explicit?.hashCode() ?: 0)
        result = 31 * result + (block?.hashCode() ?: 0)
        result = 31 * result + (season ?: 0)
        result = 31 * result + (episode ?: 0)
        result = 31 * result + (episodeType?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (subtitle?.hashCode() ?: 0)
        result = 31 * result + (summary?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakeEpisodeITunesBuilder(title=$title, duration=$duration, imageBuilder=$imageBuilder, explicit=$explicit, block=$block, " +
            "season=$season, episode=$episode, episodeType=$episodeType, author=$author, subtitle=$subtitle, summary=$summary)"
}
