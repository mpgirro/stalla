package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeBitloveBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

internal class FakeEpisodeBitloveBuilder : FakeBuilder<Episode.Bitlove>(), EpisodeBitloveBuilder {

    var guid: String? = null

    override fun guid(guid: String): EpisodeBitloveBuilder = apply { this.guid = guid }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeBitloveBuilder) return false

        if (guid != other.guid) return false

        return true
    }

    override fun hashCode(): Int {
        return guid?.hashCode() ?: 0
    }

    override fun toString() = "FakeEpisodeBitloveBuilder(guid=$guid)"
}
