package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.model.Podcast

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastAtomBuilder) return false

        if (authorBuilders != other.authorBuilders) return false
        if (contributorBuilders != other.contributorBuilders) return false
        if (linkBuilders != other.linkBuilders) return false

        return true
    }

    override fun hashCode(): Int {
        var result = authorBuilders.hashCode()
        result = 31 * result + contributorBuilders.hashCode()
        result = 31 * result + linkBuilders.hashCode()
        return result
    }

    override fun toString() =
        "FakePodcastAtomBuilder(authorBuilders=$authorBuilders, contributorBuilders=$contributorBuilders, linkBuilders=$linkBuilders)"
}
