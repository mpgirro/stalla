package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.StyledDuration
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [EpisodeItunes] instances.
 *
 * @since 1.0.0
 */
public interface EpisodeItunesBuilder : Builder<EpisodeItunes> {

    /** Set the title value. */
    public fun title(title: String?): EpisodeItunesBuilder

    /** Set the duration value. */
    public fun duration(duration: StyledDuration?): EpisodeItunesBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeItunesBuilder

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean?): EpisodeItunesBuilder

    /** Set the block flag value. */
    public fun block(block: Boolean): EpisodeItunesBuilder

    /** Set the season value. */
    public fun season(season: Int?): EpisodeItunesBuilder

    /** Set the episode value. */
    public fun episode(episode: Int?): EpisodeItunesBuilder

    /** Set the episodeType value. */
    public fun episodeType(episodeType: String?): EpisodeItunesBuilder

    /** Set the author value. */
    public fun author(author: String?): EpisodeItunesBuilder

    /** Set the subtitle value. */
    public fun subtitle(subtitle: String?): EpisodeItunesBuilder

    /** Set the summary value. */
    public fun summary(summary: String?): EpisodeItunesBuilder

    override fun applyFrom(prototype: EpisodeItunes?): EpisodeItunesBuilder =
        whenNotNull(prototype) { itunes ->
            title(itunes.title)
            duration(itunes.duration)
            imageBuilder(HrefOnlyImage.builder().applyFrom(itunes.image))
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
