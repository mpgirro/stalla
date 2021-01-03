package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeAtomBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeAtomBuilder : FakeBuilder<Episode.Atom>(), EpisodeAtomBuilder {

    var authorBuilders: MutableList<PersonBuilder> = mutableListOf()
    var contributorBuilders: MutableList<PersonBuilder> = mutableListOf()
    var linkBuilders: MutableList<LinkBuilder> = mutableListOf()

    override fun addAuthorBuilder(authorBuilder: PersonBuilder): EpisodeAtomBuilder = apply {
        authorBuilders.add(authorBuilder)
    }

    override fun addContributorBuilder(contributorBuilder: PersonBuilder): EpisodeAtomBuilder = apply {
        contributorBuilders.add(contributorBuilder)
    }

    override fun addLinkBuilder(linkBuilder: LinkBuilder): EpisodeAtomBuilder = apply {
        linkBuilders.add(linkBuilder)
    }
}
