package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.StyledDuration
import dev.stalla.model.podcastindex.Soundbite
import dev.stalla.util.whenNotNull

/** Builder for constructing [Soundbite] instances. */
public interface EpisodePodcastindexSoundbiteBuilder : Builder<Soundbite> {

    /** Set the starTime value. */
    public fun startTime(startTime: StyledDuration.SecondsAndFraction): EpisodePodcastindexSoundbiteBuilder

    /** Set the duration value. */
    public fun duration(duration: StyledDuration.SecondsAndFraction): EpisodePodcastindexSoundbiteBuilder

    /** Set the title value. */
    public fun title(title: String?): EpisodePodcastindexSoundbiteBuilder

    override fun applyFrom(prototype: Soundbite?): EpisodePodcastindexSoundbiteBuilder =
        whenNotNull(prototype) { soundbite ->
            startTime(soundbite.startTime)
            duration(soundbite.duration)
            title(soundbite.title)
        }
}
