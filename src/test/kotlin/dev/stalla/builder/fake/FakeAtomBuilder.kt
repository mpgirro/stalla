package dev.stalla.builder.fake

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.model.Atom

internal class FakeAtomBuilder : FakeBuilder<Atom>(), AtomBuilder {

    var authorBuilders: MutableList<PersonBuilder> = mutableListOf()
    var contributorBuilders: MutableList<PersonBuilder> = mutableListOf()
    var linkBuilders: MutableList<LinkBuilder> = mutableListOf()

    override fun addAuthorBuilder(authorBuilder: PersonBuilder): AtomBuilder = apply {
        authorBuilders.add(authorBuilder)
    }

    override fun addContributorBuilder(contributorBuilder: PersonBuilder): AtomBuilder = apply {
        contributorBuilders.add(contributorBuilder)
    }

    override fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder = apply {
        linkBuilders.add(linkBuilder)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeAtomBuilder) return false

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
        "FakeAtomBuilder(authorBuilders=$authorBuilders, contributorBuilders=$contributorBuilders, linkBuilders=$linkBuilders)"
}
