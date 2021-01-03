package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeITunesBuilder : FakeBuilder<Episode.ITunes>(), EpisodeITunesBuilder {

    var title: String? = null
    var duration: String? = null
    var image: Image? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var season: Int? = null
    var episode: Int? = null
    var episodeType: Episode.ITunes.EpisodeType? = null

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
    override fun episodeType(episodeType: String?): EpisodeITunesBuilder = apply { this.episodeType = Episode.ITunes.EpisodeType.of(episodeType) }
}
