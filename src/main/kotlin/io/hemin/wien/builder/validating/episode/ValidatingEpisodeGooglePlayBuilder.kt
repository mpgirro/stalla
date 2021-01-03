package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image

/** Builder class for [Episode.GooglePlay] instances. */
internal class ValidatingEpisodeGooglePlayBuilder : EpisodeGooglePlayBuilder {

    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean? = null
    private var image: Image? = null

    /** Set the description value. */
    override fun description(description: String?): EpisodeGooglePlayBuilder = apply { this.description = description }

    /** Set the explicit value. */
    override fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder = apply { this.explicit = explicit }

    /** Set the block value. */
    override fun block(block: Boolean?): EpisodeGooglePlayBuilder = apply { this.block = block }

    /** Set the Image. */
    override fun image(image: Image?): EpisodeGooglePlayBuilder = apply { this.image = image }

    override fun build(): Episode.GooglePlay? {
        if (allNull(description, explicit, block, image)) {
            return null
        }

        return Episode.GooglePlay(
            description = description,
            explicit = explicit,
            block = block,
            image = image
        )
    }
}
