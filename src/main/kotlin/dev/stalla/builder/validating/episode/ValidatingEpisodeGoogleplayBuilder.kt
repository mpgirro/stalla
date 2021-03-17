package dev.stalla.builder.validating.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeGoogleplayBuilder
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.model.googleplay.ExplicitType
import dev.stalla.util.InternalAPI
import dev.stalla.util.anyNotNull

@InternalAPI
internal class ValidatingEpisodeGoogleplayBuilder : EpisodeGoogleplayBuilder {

    private var author: String? = null
    private var description: String? = null
    private var explicit: ExplicitType? = null
    private var block: Boolean = false
    private var imageBuilder: HrefOnlyImageBuilder? = null

    override fun author(author: String?): EpisodeGoogleplayBuilder = apply { this.author = author }

    override fun description(description: String?): EpisodeGoogleplayBuilder = apply { this.description = description }

    override fun explicit(explicit: ExplicitType?): EpisodeGoogleplayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeGoogleplayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGoogleplayBuilder =
        apply { this.imageBuilder = imageBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = block || anyNotNull(author, description, explicit) || imageBuilder?.hasEnoughDataToBuild == true

    override fun build(): EpisodeGoogleplay? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return EpisodeGoogleplay(
            author = author,
            description = description,
            explicit = explicit,
            block = block,
            image = imageBuilder?.build()
        )
    }
}
