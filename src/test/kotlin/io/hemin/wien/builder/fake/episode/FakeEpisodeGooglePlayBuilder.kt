package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeGooglePlayBuilder : FakeBuilder<Episode.GooglePlay>(), EpisodeGooglePlayBuilder {

    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var image: Image? = null

    /** Set the description value. */
    override fun description(description: String?): EpisodeGooglePlayBuilder = apply { this.description = description }

    /** Set the explicit value. */
    override fun explicit(explicit: Boolean?): EpisodeGooglePlayBuilder = apply { this.explicit = explicit }

    /** Set the block value. */
    override fun block(block: Boolean?): EpisodeGooglePlayBuilder = apply { this.block = block }

    /** Set the Image. */
    override fun image(image: Image?): EpisodeGooglePlayBuilder = apply { this.image = image }
}
