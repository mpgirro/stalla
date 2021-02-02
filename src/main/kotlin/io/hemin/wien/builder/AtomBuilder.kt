package io.hemin.wien.builder

import io.hemin.wien.model.Atom
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person

/** Builder for constructing [Atom] instances. */
interface AtomBuilder : Builder<Atom> {

    /**
     * Adds a person builder to the list of authors.
     *
     * @param authorBuilder The author builder to add.
     */
    fun addAuthorBuilder(authorBuilder: PersonBuilder): AtomBuilder

    /**
     * Adds all person builders to the list of authors.
     *
     * @param authorBuilders The author builder list to add.
     */
    fun addAuthorBuilders(authorBuilders: List<PersonBuilder>): AtomBuilder = apply {
        authorBuilders.forEach { authorBuilder -> addAuthorBuilder(authorBuilder) }
    }

    /**
     * Adds a person builder to the list of contributors.
     *
     * @param contributorBuilder The contributor builder to add.
     */
    fun addContributorBuilder(contributorBuilder: PersonBuilder): AtomBuilder

    /**
     * Adds all person builders to the list of contributors.
     *
     * @param contributorBuilders The contributor builder list to add.
     */
    fun addContributorBuilders(contributorBuilders: List<PersonBuilder>): AtomBuilder = apply {
        contributorBuilders.forEach { contributorBuilder -> addContributorBuilder(contributorBuilder) }
    }

    /**
     * Adds a link builder to the list of links.
     *
     * @param linkBuilder The link builder to add.
     */
    fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder

    /**
     * Adds all link builders to the list of links.
     *
     * @param linkBuilders The link builder list to add.
     */
    fun addLinkBuilders(linkBuilders: List<LinkBuilder>): AtomBuilder = apply {
        linkBuilders.forEach { linkBuilder -> addLinkBuilder(linkBuilder) }
    }

    override fun from(model: Atom): AtomBuilder {
        return Atom.builder()
            .addAuthorBuilders(model.authors.map { author -> Person.builder().from(author)})
            .addContributorBuilders(model.contributors.map { contributor -> Person.builder().from(contributor) })
            .addLinkBuilders(model.links.map { link -> Link.builder().from(link) })
    }
}
