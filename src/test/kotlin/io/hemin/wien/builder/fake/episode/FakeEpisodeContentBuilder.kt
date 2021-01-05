package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeContentBuilder : FakeBuilder<Episode.Content>(), EpisodeContentBuilder {

    var encoded: String? = null

    override fun encoded(encoded: String): EpisodeContentBuilder = apply { this.encoded = encoded }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeContentBuilder) return false

        if (encoded != other.encoded) return false

        return true
    }

    override fun hashCode(): Int {
        return encoded?.hashCode() ?: 0
    }

    override fun toString() = "FakeEpisodeContentBuilder(encoded=$encoded)"
}
