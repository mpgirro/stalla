package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.util.whenNotNull

/** Builder for constructing [EpisodeGoogleplay] instances. */
public interface EpisodeGoogleplayBuilder : Builder<EpisodeGoogleplay> {

    /** Set the description value. */
    public fun description(description: String?): EpisodeGoogleplayBuilder

    /** Set the explicit value. */
    public fun explicit(explicit: Boolean?): EpisodeGoogleplayBuilder

    /** Set the block value. */
    public fun block(block: Boolean): EpisodeGoogleplayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGoogleplayBuilder

    override fun from(model: EpisodeGoogleplay?): EpisodeGoogleplayBuilder = whenNotNull(model) { googlePlay ->
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
