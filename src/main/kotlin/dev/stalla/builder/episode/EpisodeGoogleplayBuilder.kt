package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.model.googleplay.ExplicitType
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [EpisodeGoogleplay] instances.
 *
 * @since 1.0.0
 */
public interface EpisodeGoogleplayBuilder : Builder<EpisodeGoogleplay> {

    /** Set the author value. */
    public fun author(author: String?): EpisodeGoogleplayBuilder

    /** Set the description value. */
    public fun description(description: String?): EpisodeGoogleplayBuilder

    /** Set the explicit value based on it's string representation. */
    public fun explicit(explicit: String?): EpisodeGoogleplayBuilder =
        apply { explicit(ExplicitType.of(explicit)) }

    /** Set the explicit value. */
    public fun explicit(explicit: ExplicitType?): EpisodeGoogleplayBuilder

    /** Set the block value. */
    public fun block(block: Boolean): EpisodeGoogleplayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGoogleplayBuilder

    override fun applyFrom(prototype: EpisodeGoogleplay?): EpisodeGoogleplayBuilder =
        whenNotNull(prototype) { googleplay ->
            author(googleplay.author)
            description(googleplay.description)
            explicit(googleplay.explicit?.type)
            block(googleplay.block)
            imageBuilder(HrefOnlyImage.builder().applyFrom(googleplay.image))
        }
}
