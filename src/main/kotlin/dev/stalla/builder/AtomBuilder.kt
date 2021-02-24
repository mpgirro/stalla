package dev.stalla.builder

import dev.stalla.model.Person
import dev.stalla.model.atom.Atom
import dev.stalla.model.atom.Link
import dev.stalla.util.whenNotNull

/** Builder for constructing [Atom] instances. */
public interface AtomBuilder : Builder<Atom> {

    /** Adds the [PersonBuilder] to the list of author builders. */
    public fun addAuthorBuilder(authorBuilder: PersonBuilder): AtomBuilder

    /** Adds all of the [PersonBuilder] to the list of author builders. */
    public fun addAllAuthorBuilder(authorBuilders: List<PersonBuilder>): AtomBuilder =
        apply { authorBuilders.forEach(::addAuthorBuilder) }

    /** Adds the [PersonBuilder] to the list of contributor builders. */
    public fun addContributorBuilder(contributorBuilder: PersonBuilder): AtomBuilder

    /** Adds all of the [PersonBuilder] to the list of contributor builders. */
    public fun addAllContributorBuilder(contributorBuilders: List<PersonBuilder>): AtomBuilder =
        apply { contributorBuilders.forEach(::addContributorBuilder) }

    /** Adds the [LinkBuilder] to the list of links. */
    public fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder

    /** Adds all of the [LinkBuilder] to the list of links. */
    public fun addAllLinkBuilder(linkBuilders: List<LinkBuilder>): AtomBuilder =
        apply { linkBuilders.forEach(::addLinkBuilder) }

    override fun applyFrom(prototype: Atom?): AtomBuilder = whenNotNull(prototype) { atom ->
        addAllAuthorBuilder(atom.authors.map(Person.builder()::applyFrom))
        addAllContributorBuilder(atom.contributors.map(Person.builder()::applyFrom))
        addAllLinkBuilder(atom.links.map(Link.builder()::applyFrom))
    }
}
