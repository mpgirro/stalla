package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.model.Episode

internal interface EpisodeGooglePlayBuilder : Builder<Episode.GooglePlay> {

    /** Set the description value. */
    fun description(description: String?): EpisodeGooglePlayBuilder

    /** Set the explicit value. */
    fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder

    /** Set the block value. */
    fun block(block: Boolean?): EpisodeGooglePlayBuilder

    /** Set the Image. */
    fun imageBuilder(imageBuilder: ImageBuilder?): EpisodeGooglePlayBuilder
}
