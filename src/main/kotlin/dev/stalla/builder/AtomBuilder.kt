package dev.stalla.builder

import dev.stalla.model.atom.Atom
import dev.stalla.model.atom.AtomPerson
import dev.stalla.model.atom.Link
import dev.stalla.util.whenNotNull

/** Builder for constructing [Atom] instances. */
public interface AtomBuilder : Builder<Atom> {

    /** Adds the [AtomPersonBuilder] to the list of author builders. */
    public fun addAuthorBuilder(authorBuilder: AtomPersonBuilder): AtomBuilder

    /** Adds all of the [AtomPersonBuilder] to the list of author builders. */
    public fun addAllAuthorBuilders(authorBuilders: List<AtomPersonBuilder>): AtomBuilder =
        apply { authorBuilders.forEach(::addAuthorBuilder) }

    /** Adds the [AtomPersonBuilder] to the list of contributor builders. */
    public fun addContributorBuilder(contributorBuilder: AtomPersonBuilder): AtomBuilder

    /** Adds all of the [AtomPersonBuilder] to the list of contributor builders. */
    public fun addAllContributorBuilders(contributorBuilders: List<AtomPersonBuilder>): AtomBuilder =
        apply { contributorBuilders.forEach(::addContributorBuilder) }

    /** Adds the [LinkBuilder] to the list of links. */
    public fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder

    /** Adds all of the [LinkBuilder] to the list of links. */
    public fun addAllLinkBuilders(linkBuilders: List<LinkBuilder>): AtomBuilder =
        apply { linkBuilders.forEach(::addLinkBuilder) }

    override fun applyFrom(prototype: Atom?): AtomBuilder =
        whenNotNull(prototype) { atom ->
            addAllAuthorBuilders(atom.authors.map(AtomPerson.builder()::applyFrom))
            addAllContributorBuilders(atom.contributors.map(AtomPerson.builder()::applyFrom))
            addAllLinkBuilders(atom.links.map(Link.builder()::applyFrom))
        }
}
