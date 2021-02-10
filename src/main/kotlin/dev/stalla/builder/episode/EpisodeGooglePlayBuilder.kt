package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.util.whenNotNull

/** Builder for constructing [EpisodeGoogleplay] instances. */
public interface EpisodeGooglePlayBuilder : Builder<EpisodeGoogleplay> {

    /** Set the description value. */
    public fun description(description: String?): EpisodeGooglePlayBuilder

    /** Set the explicit value. */
    public fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder

    /** Set the block value. */
    public fun block(block: Boolean): EpisodeGooglePlayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGooglePlayBuilder

    override fun from(model: EpisodeGoogleplay?): EpisodeGooglePlayBuilder = whenNotNull(model) { googlePlay ->
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
