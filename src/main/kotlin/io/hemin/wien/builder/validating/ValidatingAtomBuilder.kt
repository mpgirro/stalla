package io.hemin.wien.builder.validating

import io.hemin.wien.builder.AtomBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Atom

internal class ValidatingAtomBuilder : AtomBuilder {

    private var authorBuilders: MutableList<PersonBuilder> = mutableListOf()
    private var contributorBuilders: MutableList<PersonBuilder> = mutableListOf()
    private var linkBuilders: MutableList<LinkBuilder> = mutableListOf()

    override fun addAuthorBuilder(authorBuilder: PersonBuilder): AtomBuilder = apply {
        authorBuilders.add(authorBuilder)
    }

    override fun addAuthorBuilders(authorBuilders: List<PersonBuilder>): AtomBuilder = apply {
        this.authorBuilders.addAll(authorBuilders)
    }

    override fun addContributorBuilder(contributorBuilder: PersonBuilder): AtomBuilder = apply {
        contributorBuilders.add(contributorBuilder)
    }

    override fun addContributorBuilders(contributorBuilders: List<PersonBuilder>): AtomBuilder = apply {
        this.contributorBuilders.addAll(contributorBuilders)
    }

    override fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder = apply {
        linkBuilders.add(linkBuilder)
    }

    override fun addLinkBuilders(linkBuilders: List<LinkBuilder>): AtomBuilder = apply {
        this.linkBuilders.addAll(linkBuilders)
    }

    override val hasEnoughDataToBuild: Boolean
        get() = (authorBuilders + contributorBuilders + linkBuilders).any { it.hasEnoughDataToBuild }

    override fun build(): Atom? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val authors = authorBuilders.mapNotNull { it.build() }
        val contributors = contributorBuilders.mapNotNull { it.build() }
        val links = linkBuilders.mapNotNull { it.build() }
        return Atom(authors, contributors, links)
    }
}
