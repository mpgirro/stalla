package dev.stalla.builder.validating.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeGoogleplayBuilder
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.util.anyNotNull

internal class ValidatingEpisodeGoogleplayBuilder : EpisodeGoogleplayBuilder {

    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean = false
    private var imageBuilder: HrefOnlyImageBuilder? = null

    override fun description(description: String?): EpisodeGoogleplayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): EpisodeGoogleplayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeGoogleplayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGoogleplayBuilder = apply { this.imageBuilder = imageBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = block || anyNotNull(description, explicit) || imageBuilder?.hasEnoughDataToBuild == true

    override fun build(): EpisodeGoogleplay? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val image = imageBuilder?.build()
        return EpisodeGoogleplay(description, explicit, block, image)
    }
}
