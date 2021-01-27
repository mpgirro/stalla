package io.hemin.wien.builder

import io.hemin.wien.model.Atom

interface AtomBuilder : Builder<Atom> {

    /**
     * Adds a person builder to the list of authors.
     *
     * @param authorBuilder The author builder to add.
     */
    fun addAuthorBuilder(authorBuilder: PersonBuilder): AtomBuilder

    /**
     * Adds a person builder to the list of contributors.
     *
     * @param contributorBuilder The contributor builder to add.
     */
    fun addContributorBuilder(contributorBuilder: PersonBuilder): AtomBuilder

    /**
     * Adds a link builder to the list of links.
     *
     * @param linkBuilder The link builder to add.
     */
    fun addLinkBuilder(linkBuilder: LinkBuilder): AtomBuilder
}
