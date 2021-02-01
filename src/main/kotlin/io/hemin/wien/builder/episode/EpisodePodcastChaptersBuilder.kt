package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

/** Builder for constructing [Episode.Podcast.Chapters] instances. */
interface EpisodePodcastChaptersBuilder : Builder<Episode.Podcast.Chapters> {

    fun url(url: String): EpisodePodcastChaptersBuilder

    fun type(type: String): EpisodePodcastChaptersBuilder
}
