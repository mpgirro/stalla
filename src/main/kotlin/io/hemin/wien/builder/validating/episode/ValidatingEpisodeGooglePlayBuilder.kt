package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodeGooglePlayBuilder : EpisodeGooglePlayBuilder {

    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean? = null
    private var imageBuilder: ImageBuilder? = null

    override fun description(description: String?): EpisodeGooglePlayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): EpisodeGooglePlayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: ImageBuilder?): EpisodeGooglePlayBuilder = apply { this.imageBuilder = imageBuilder }

    override fun build(): Episode.GooglePlay? {
        if (allNull(description, explicit, block, imageBuilder)) {
            return null
        }

        return Episode.GooglePlay(
            description = description,
            explicit = explicit,
            block = block,
            image = imageBuilder?.build()
        )
    }
}
