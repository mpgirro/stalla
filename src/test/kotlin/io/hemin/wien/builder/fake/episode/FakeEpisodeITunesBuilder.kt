package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

internal class FakeEpisodeITunesBuilder : FakeBuilder<Episode.ITunes>(), EpisodeITunesBuilder {

    var title: String? = null
    var duration: String? = null
    var imageBuilder: HrefOnlyImageBuilder? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var season: Int? = null
    var episode: Int? = null
    var episodeType: Episode.ITunes.EpisodeType? = null
    var author: String? = null
    var subtitle: String? = null
    var summary: String? = null

    override fun title(title: String?): EpisodeITunesBuilder = apply { this.title = title }

    override fun duration(duration: String?): EpisodeITunesBuilder = apply { this.duration = duration }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeITunesBuilder = apply { this.imageBuilder = imageBuilder }

    override fun explicit(explicit: Boolean?): EpisodeITunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): EpisodeITunesBuilder = apply { this.block = block }

    override fun season(season: Int?): EpisodeITunesBuilder = apply { this.season = season }

    override fun episode(episode: Int?): EpisodeITunesBuilder = apply { this.episode = episode }

    override fun episodeType(episodeType: String?): EpisodeITunesBuilder = apply { this.episodeType = Episode.ITunes.EpisodeType.of(episodeType) }

    override fun author(author: String?): EpisodeITunesBuilder = apply { this.author = author }

    override fun subtitle(subtitle: String?): EpisodeITunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): EpisodeITunesBuilder = apply { this.summary = summary }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeITunesBuilder) return false

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
