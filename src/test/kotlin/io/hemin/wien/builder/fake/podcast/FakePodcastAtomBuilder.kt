package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastAtomBuilder : FakeBuilder<Podcast.Atom>(), PodcastAtomBuilder {

    var authorBuilders: MutableList<PersonBuilder> = mutableListOf()
    var contributorBuilders: MutableList<PersonBuilder> = mutableListOf()
    var linkBuilders: MutableList<LinkBuilder> = mutableListOf()

    override fun addAuthorBuilder(authorBuilder: PersonBuilder): PodcastAtomBuilder = apply {
        authorBuilders.add(authorBuilder)
    }

    override fun addContributorBuilder(contributorBuilder: PersonBuilder): PodcastAtomBuilder = apply {
        contributorBuilders.add(contributorBuilder)
    }

    override fun addLinkBuilder(linkBuilder: LinkBuilder): PodcastAtomBuilder = apply {
        linkBuilders.add(linkBuilder)
    }
}
