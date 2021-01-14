package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Episode

interface EpisodeAtomBuilder : Builder<Episode.Atom> {

    /**
     * Adds a person builder to the list of authors.
     *
     * @param authorBuilder The author builder to add.
     */
    fun addAuthorBuilder(authorBuilder: PersonBuilder): EpisodeAtomBuilder

    /**
     * Adds a person builder to the list of contributors.
     *
     * @param contributorBuilder The contributor builder to add.
     */
    fun addContributorBuilder(contributorBuilder: PersonBuilder): EpisodeAtomBuilder

    /**
     * Adds a link builder to the list of links.
     *
     * @param linkBuilder The link builder to add.
     */
    fun addLinkBuilder(linkBuilder: LinkBuilder): EpisodeAtomBuilder
}
