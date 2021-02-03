package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.GooglePlay] instances. */
interface EpisodeGooglePlayBuilder : Builder<Episode.GooglePlay> {

    /** Set the description value. */
    fun description(description: String?): EpisodeGooglePlayBuilder

    /** Set the explicit value. */
    fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder

    /** Set the block value. */
    fun block(block: Boolean): EpisodeGooglePlayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGooglePlayBuilder

    override fun from(model: Episode.GooglePlay?): EpisodeGooglePlayBuilder = whenNotNull(model) { googlePlay ->
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
