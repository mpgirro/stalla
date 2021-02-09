package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodeContentBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.Episode

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
