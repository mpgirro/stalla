package dev.stalla.builder.validating.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeGoogleplayBuilder2
import dev.stalla.model.googleplay.EpisodeGoogleplay2
import dev.stalla.util.anyNotNull

internal class ValidatingEpisodeGoogleplayBuilder2 : EpisodeGoogleplayBuilder2 {

    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean = false
    private var imageBuilder: HrefOnlyImageBuilder? = null

    override fun description(description: String?): EpisodeGoogleplayBuilder2 = apply { this.description = description }

    override fun explicit(explicit: Boolean?): EpisodeGoogleplayBuilder2 = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeGoogleplayBuilder2 = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGoogleplayBuilder2 = apply { this.imageBuilder = imageBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = block || anyNotNull(description, explicit) || imageBuilder?.hasEnoughDataToBuild == true

    override fun build(): EpisodeGoogleplay2? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val image = imageBuilder?.build()
        return EpisodeGoogleplay2(description, explicit, block, image)
    }
}
