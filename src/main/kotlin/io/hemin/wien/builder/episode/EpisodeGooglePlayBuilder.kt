package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.GooglePlay] instances. */
public interface EpisodeGooglePlayBuilder : Builder<Episode.GooglePlay> {

    /** Set the description value. */
    public fun description(description: String?): EpisodeGooglePlayBuilder

    /** Set the explicit value. */
    public fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder

    /** Set the block value. */
    public fun block(block: Boolean): EpisodeGooglePlayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGooglePlayBuilder

    override fun from(model: Episode.GooglePlay?): EpisodeGooglePlayBuilder = whenNotNull(model) { googlePlay ->
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
