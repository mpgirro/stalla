package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeContentBuilder : FakeBuilder<Episode.Content>(), EpisodeContentBuilder {

    var encoded: String? = null

    /** Set the encoded value. */
    override fun encoded(encoded: String): EpisodeContentBuilder = apply { this.encoded = encoded }
}
