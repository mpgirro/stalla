package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.util.whenNotNull

/** Builder for constructing [Chapters] instances. */
public interface EpisodePodcastindexChaptersBuilder : Builder<Chapters> {

    /** Set the url value . */
    public fun url(url: String): EpisodePodcastindexChaptersBuilder

    /** Set the type value. */
    public fun type(type: String): EpisodePodcastindexChaptersBuilder

    override fun from(model: Chapters?): EpisodePodcastindexChaptersBuilder = whenNotNull(model) { chapters ->
        url(chapters.url)
        type(chapters.type)
    }
}
