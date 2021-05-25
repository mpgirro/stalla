package dev.stalla.builder.validating

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.model.atom.Atom
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

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
            authors = authorBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            contributors = contributorBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            links = linkBuilders.mapNotNull { it.build() }.asUnmodifiable()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingAtomBuilder) return false

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

    override fun toString(): String =
        "ValidatingAtomBuilder(authorBuilders=$authorBuilders, contributorBuilders=$contributorBuilders, linkBuilders=$linkBuilders)"
}
