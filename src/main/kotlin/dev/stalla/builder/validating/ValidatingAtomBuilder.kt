package dev.stalla.builder.validating

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.model.atom.Atom
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingAtomBuilder : AtomBuilder {

    private var authorBuilders: MutableList<AtomPersonBuilder> = mutableListOf()
    private var contributorBuilders: MutableList<AtomPersonBuilder> = mutableListOf()
    private var linkBuilders: MutableList<LinkBuilder> = mutableListOf()

    override fun addAuthorBuilder(authorBuilder: AtomPersonBuilder): AtomBuilder =
        apply { authorBuilders.add(authorBuilder) }

    override fun addContributorBuilder(contributorBuilder: AtomPersonBuilder): AtomBuilder =
        apply { contributorBuilders.add(contributorBuilder) }

    override fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder =
        apply { linkBuilders.add(linkBuilder) }

    override val hasEnoughDataToBuild: Boolean
        get() = (authorBuilders + contributorBuilders + linkBuilders).any { it.hasEnoughDataToBuild }

    override fun build(): Atom? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Atom(
            authors = authorBuilders.mapNotNull { it.build() },
            contributors = contributorBuilders.mapNotNull { it.build() },
            links = linkBuilders.mapNotNull { it.build() }
        )
    }
}
