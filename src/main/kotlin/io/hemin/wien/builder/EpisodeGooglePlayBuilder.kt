package io.hemin.wien.builder

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image

/** Builder class for [Episode.GooglePlay] instances. */
class EpisodeGooglePlayBuilder : Builder<Episode.GooglePlay> {

    private var description: String? = null
    private var duration: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean? = null
    private var image: Image? = null

    /** Set the description value. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the duration value. */
    fun duration(duration: String?) = apply { this.duration = duration }

    /** Set the explicit value. */
    fun explicit(explicit: Boolean?) = apply { this.explicit = explicit }

    /** Set the block value. */
    fun block(block: Boolean?) = apply { this.block = block }

    /** Set the Image. */
    fun image(image: Image?) = apply { this.image = image }

    override fun build(): Episode.GooglePlay? {
        return if (anyNotNull(description, duration, explicit, block, image)) {
            return Episode.GooglePlay(
                description = description,
                duration = duration,
                explicit = explicit,
                block = block,
                image = image
            )
        } else {
            null
        }
    }
}
