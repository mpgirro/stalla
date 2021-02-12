package dev.stalla.builder.fake.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeGoogleplayBuilder2
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.googleplay.EpisodeGoogleplay2

internal class FakeEpisodeGoogleplayBuilder2 : FakeBuilder<EpisodeGoogleplay2>(), EpisodeGoogleplayBuilder2 {

    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var imageBuilder: HrefOnlyImageBuilder? = null

    override fun description(description: String?): EpisodeGoogleplayBuilder2 = apply { this.description = description }

    override fun explicit(explicit: Boolean?): EpisodeGoogleplayBuilder2 = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeGoogleplayBuilder2 = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGoogleplayBuilder2 = apply { this.imageBuilder = imageBuilder }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeGoogleplayBuilder2) return false

        if (description != other.description) return false
        if (explicit != other.explicit) return false
        if (block != other.block) return false
        if (imageBuilder != other.imageBuilder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description?.hashCode() ?: 0
        result = 31 * result + (explicit?.hashCode() ?: 0)
        result = 31 * result + (block?.hashCode() ?: 0)
        result = 31 * result + (imageBuilder?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakeEpisodeGooglePlayBuilder(description=$description, explicit=$explicit, block=$block, imageBuilder=$imageBuilder)"
}
