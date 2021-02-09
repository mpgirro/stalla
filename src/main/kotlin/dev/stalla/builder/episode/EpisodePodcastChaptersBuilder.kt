package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Episode
import dev.stalla.util.whenNotNull

/** Builder for constructing [Episode.Podcast.Chapters] instances. */
public interface EpisodePodcastChaptersBuilder : Builder<Episode.Podcast.Chapters> {

    /** Set the url value . */
    public fun url(url: String): EpisodePodcastChaptersBuilder

    /** Set the type value. */
    public fun type(type: String): EpisodePodcastChaptersBuilder

    override fun from(model: Episode.Podcast.Chapters?): EpisodePodcastChaptersBuilder = whenNotNull(model) { chapters ->
        url(chapters.url)
        type(chapters.type)
    }
}
