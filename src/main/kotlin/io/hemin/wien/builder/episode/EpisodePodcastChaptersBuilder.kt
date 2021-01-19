package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

internal interface EpisodePodcastChaptersBuilder: Builder<Episode.Podcast.Chapters> {

    fun url(url: String): EpisodePodcastChaptersBuilder

    fun type(type: String): EpisodePodcastChaptersBuilder
}
