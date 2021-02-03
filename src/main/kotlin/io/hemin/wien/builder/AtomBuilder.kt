package io.hemin.wien.builder

import io.hemin.wien.model.Atom
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Atom] instances. */
interface AtomBuilder : Builder<Atom> {

    /** Adds a [PersonBuilder] to the list of author builders. */
    fun addAuthorBuilder(authorBuilder: PersonBuilder): AtomBuilder

    /** Adds multiple [PersonBuilder] to the list of author builders. */
    fun addAuthorBuilders(authorBuilders: List<PersonBuilder>): AtomBuilder = apply {
        authorBuilders.forEach { authorBuilder -> addAuthorBuilder(authorBuilder) }
    }

    /** Adds a [PersonBuilder] to the list of contributor builders. */
    fun addContributorBuilder(contributorBuilder: PersonBuilder): AtomBuilder

    /** Adds multiple [PersonBuilder] to the list of contributor builders. */
    fun addContributorBuilders(contributorBuilders: List<PersonBuilder>): AtomBuilder = apply {
        contributorBuilders.forEach { contributorBuilder -> addContributorBuilder(contributorBuilder) }
    }

    /** Adds a [LinkBuilder] to the list of links. */
    fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder

    /** Adds multiple [LinkBuilder] to the list of links. */
    fun addLinkBuilders(linkBuilders: List<LinkBuilder>): AtomBuilder = apply {
        linkBuilders.forEach { linkBuilder -> addLinkBuilder(linkBuilder) }
    }

    override fun from(model: Atom?): AtomBuilder = whenNotNull(model) { atom ->
        addAuthorBuilders(atom.authors.map { author -> Person.builder().from(author) })
        addContributorBuilders(atom.contributors.map { contributor -> Person.builder().from(contributor) })
        addLinkBuilders(atom.links.map { link -> Link.builder().from(link) })
    }
}
