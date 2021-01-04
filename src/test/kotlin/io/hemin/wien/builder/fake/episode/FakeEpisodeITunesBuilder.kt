package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeITunesBuilder : FakeBuilder<Episode.ITunes>(), EpisodeITunesBuilder {

    var title: String? = null
    var duration: String? = null
    var imageBuilder: ImageBuilder? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var season: Int? = null
    var episode: Int? = null
    var episodeType: Episode.ITunes.EpisodeType? = null

    override fun title(title: String?): EpisodeITunesBuilder = apply { this.title = title }

    override fun duration(duration: String?): EpisodeITunesBuilder = apply { this.duration = duration }

    override fun imageBuilder(imageBuilder: ImageBuilder?): EpisodeITunesBuilder = apply { this.imageBuilder = imageBuilder }

    override fun explicit(explicit: Boolean?): EpisodeITunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): EpisodeITunesBuilder = apply { this.block = block }

    override fun season(season: Int?): EpisodeITunesBuilder = apply { this.season = season }

    override fun episode(episode: Int?): EpisodeITunesBuilder = apply { this.episode = episode }

    override fun episodeType(episodeType: String?): EpisodeITunesBuilder = apply { this.episodeType = Episode.ITunes.EpisodeType.of(episodeType) }
}
