package io.hemin.wien.builder

import io.hemin.wien.model.Atom
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Atom] instances. */
public interface AtomBuilder : Builder<Atom> {

    /** Adds a [PersonBuilder] to the list of author builders. */
    public fun addAuthorBuilder(authorBuilder: PersonBuilder): AtomBuilder

    /** Adds multiple [PersonBuilder] to the list of author builders. */
    public fun addAuthorBuilders(authorBuilders: List<PersonBuilder>): AtomBuilder = apply {
        authorBuilders.forEach(::addAuthorBuilder)
    }

    /** Adds a [PersonBuilder] to the list of contributor builders. */
    public fun addContributorBuilder(contributorBuilder: PersonBuilder): AtomBuilder

    /** Adds multiple [PersonBuilder] to the list of contributor builders. */
    public fun addContributorBuilders(contributorBuilders: List<PersonBuilder>): AtomBuilder = apply {
        contributorBuilders.forEach(::addContributorBuilder)
    }

    /** Adds a [LinkBuilder] to the list of links. */
    public fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder

    /** Adds multiple [LinkBuilder] to the list of links. */
    public fun addLinkBuilders(linkBuilders: List<LinkBuilder>): AtomBuilder = apply {
        linkBuilders.forEach(::addLinkBuilder)
    }

    override fun from(model: Atom?): AtomBuilder = whenNotNull(model) { atom ->
        addAuthorBuilders(atom.authors.map(Person.builder()::from))
        addContributorBuilders(atom.contributors.map(Person.builder()::from))
        addLinkBuilders(atom.links.map(Link.builder()::from))
    }
}
