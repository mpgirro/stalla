package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.itunes.EpisodeItunes2
import dev.stalla.util.whenNotNull

/** Builder for constructing [EpisodeItunes2] instances. */
public interface EpisodeItunesBuilder2 : Builder<EpisodeItunes2> {

    /** Set the title value. */
    public fun title(title: String?): EpisodeItunesBuilder2

    /** Set the duration value. */
    public fun duration(duration: String?): EpisodeItunesBuilder2

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeItunesBuilder2

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean?): EpisodeItunesBuilder2

    /** Set the block flag value. */
    public fun block(block: Boolean): EpisodeItunesBuilder2

    /** Set the season value. */
    public fun season(season: Int?): EpisodeItunesBuilder2

    /** Set the episode value. */
    public fun episode(episode: Int?): EpisodeItunesBuilder2

    /** Set the episodeType value. */
    public fun episodeType(episodeType: String?): EpisodeItunesBuilder2

    /** Set the author value. */
    public fun author(author: String?): EpisodeItunesBuilder2

    /** Set the subtitle value. */
    public fun subtitle(subtitle: String?): EpisodeItunesBuilder2

    /** Set the summary value. */
    public fun summary(summary: String?): EpisodeItunesBuilder2

    override fun from(model: EpisodeItunes2?): EpisodeItunesBuilder2 = whenNotNull(model) { itunes ->
        title(itunes.title)
        duration(itunes.duration)
        imageBuilder(HrefOnlyImage.builder().from(itunes.image))
        explicit(itunes.explicit)
        block(itunes.block)
        season(itunes.season)
        episode(itunes.episode)
        episodeType(itunes.episodeType?.type)
        author(itunes.author)
        subtitle(itunes.subtitle)
        summary(itunes.summary)
    }
}
