package dev.stalla.builder.fake.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.episode.EpisodeGooglePlayBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.Episode

internal class FakeEpisodeGooglePlayBuilder : FakeBuilder<Episode.GooglePlay>(), EpisodeGooglePlayBuilder {

    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var imageBuilder: HrefOnlyImageBuilder? = null

    override fun description(description: String?): EpisodeGooglePlayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): EpisodeGooglePlayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeGooglePlayBuilder = apply { this.imageBuilder = imageBuilder }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeGooglePlayBuilder) return false

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
