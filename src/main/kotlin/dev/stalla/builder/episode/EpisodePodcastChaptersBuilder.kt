package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastns.Chapters
import dev.stalla.util.whenNotNull

/** Builder for constructing [Chapters] instances. */
public interface EpisodePodcastChaptersBuilder : Builder<Chapters> {

    /** Set the url value . */
    public fun url(url: String): EpisodePodcastChaptersBuilder

    /** Set the type value. */
    public fun type(type: String): EpisodePodcastChaptersBuilder

    override fun from(model: Chapters?): EpisodePodcastChaptersBuilder = whenNotNull(model) { chapters ->
        url(chapters.url)
        type(chapters.type)
    }
}
