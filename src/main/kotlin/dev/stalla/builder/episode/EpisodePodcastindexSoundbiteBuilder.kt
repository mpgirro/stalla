package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Soundbite
import dev.stalla.util.whenNotNull
import java.time.Duration

/** Builder for constructing [Soundbite] instances. */
public interface EpisodePodcastindexSoundbiteBuilder : Builder<Soundbite> {

    /** Set the starTime value. */
    public fun startTime(startTime: Duration): EpisodePodcastindexSoundbiteBuilder

    /** Set the duration value. */
    public fun duration(duration: Duration): EpisodePodcastindexSoundbiteBuilder

    /** Set the title value. */
    public fun title(title: String?): EpisodePodcastindexSoundbiteBuilder

    override fun from(model: Soundbite?): EpisodePodcastindexSoundbiteBuilder = whenNotNull(model) { soundbite ->
        startTime(soundbite.startTime)
        duration(soundbite.duration)
        title(soundbite.title)
    }
}
