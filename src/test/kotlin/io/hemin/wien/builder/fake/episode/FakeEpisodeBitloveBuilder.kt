package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeBitloveBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeBitloveBuilder : FakeBuilder<Episode.Bitlove>(), EpisodeBitloveBuilder {

    var guid: String? = null

    /** Set the guid value. */
    override fun guid(guid: String): EpisodeBitloveBuilder = apply { this.guid = guid }
}
