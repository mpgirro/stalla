package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.Podcast.Chapters] instances. */
interface EpisodePodcastChaptersBuilder : Builder<Episode.Podcast.Chapters> {

    /** Set the url value . */
    fun url(url: String): EpisodePodcastChaptersBuilder

    /** Set the type value. */
    fun type(type: String): EpisodePodcastChaptersBuilder

    override fun from(model: Episode.Podcast.Chapters?): EpisodePodcastChaptersBuilder = whenNotNull(model) { chapters ->
        url(chapters.url)
        type(chapters.type)
    }
}
