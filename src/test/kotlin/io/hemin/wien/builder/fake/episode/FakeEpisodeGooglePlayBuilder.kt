package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeGooglePlayBuilder : FakeBuilder<Episode.GooglePlay>(), EpisodeGooglePlayBuilder {

    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var imageBuilder: ImageBuilder? = null

    override fun description(description: String?): EpisodeGooglePlayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): EpisodeGooglePlayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: ImageBuilder?): EpisodeGooglePlayBuilder = apply { this.imageBuilder = imageBuilder }
}
