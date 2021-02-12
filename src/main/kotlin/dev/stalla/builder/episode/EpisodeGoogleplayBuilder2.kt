package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.EpisodeGoogleplay2
import dev.stalla.util.whenNotNull

/** Builder for constructing [EpisodeGoogleplay2] instances. */
public interface EpisodeGoogleplayBuilder2 : Builder<EpisodeGoogleplay2> {

    /** Set the description value. */
    public fun description(description: String?): EpisodeGoogleplayBuilder2

    /** Set the explicit value. */
    public fun explicit(explicit: Boolean?): EpisodeGoogleplayBuilder2

    /** Set the block value. */
    public fun block(block: Boolean): EpisodeGoogleplayBuilder2

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGoogleplayBuilder2

    override fun from(model: EpisodeGoogleplay2?): EpisodeGoogleplayBuilder2 = whenNotNull(model) { googlePlay ->
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
