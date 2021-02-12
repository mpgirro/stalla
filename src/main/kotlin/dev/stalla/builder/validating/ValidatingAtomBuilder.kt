package dev.stalla.builder.validating

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.model.atom.Atom

internal class ValidatingAtomBuilder : AtomBuilder {

    private var authorBuilders: MutableList<PersonBuilder> = mutableListOf()
    private var contributorBuilders: MutableList<PersonBuilder> = mutableListOf()
    private var linkBuilders: MutableList<LinkBuilder> = mutableListOf()

    override fun addAuthorBuilder(authorBuilder: PersonBuilder): AtomBuilder = apply {
        authorBuilders.add(authorBuilder)
    }

    override fun addContributorBuilder(contributorBuilder: PersonBuilder): AtomBuilder = apply {
        contributorBuilders.add(contributorBuilder)
    }

    override fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder = apply {
        linkBuilders.add(linkBuilder)
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
