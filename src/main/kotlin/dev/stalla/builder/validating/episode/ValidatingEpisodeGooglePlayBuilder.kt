package dev.stalla.builder.validating.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeGooglePlayBuilder
import dev.stalla.model.Episode
import dev.stalla.util.anyNotNull

internal class ValidatingEpisodeGooglePlayBuilder : EpisodeGooglePlayBuilder {

    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean = false
    private var imageBuilder: HrefOnlyImageBuilder? = null

    override fun description(description: String?): EpisodeGooglePlayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeGooglePlayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGooglePlayBuilder = apply { this.imageBuilder = imageBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = block || anyNotNull(description, explicit) || imageBuilder?.hasEnoughDataToBuild == true

    override fun build(): Episode.GooglePlay? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val image = imageBuilder?.build()
        return Episode.GooglePlay(description, explicit, block, image)
    }
}
