package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.MediaType
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [Chapters] instances.
 *
 * @since 1.0.0
 */
public interface EpisodePodcastindexChaptersBuilder : Builder<Chapters> {

    /** Set the url value . */
    public fun url(url: String): EpisodePodcastindexChaptersBuilder

    /** Set the type value. */
    public fun type(type: MediaType): EpisodePodcastindexChaptersBuilder

    override fun applyFrom(prototype: Chapters?): EpisodePodcastindexChaptersBuilder =
        whenNotNull(prototype) { chapters ->
            url(chapters.url)
            type(chapters.type)
        }
}
