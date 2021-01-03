package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person

internal interface EpisodeAtomBuilder : Builder<Episode.Atom> {

    /**
     * Adds a person to the list of authors.
     *
     * @param author The author to add.
     */
    fun addAuthor(author: Person): EpisodeAtomBuilder

    /**
     * Adds a person to the list of contributors.
     *
     * @param contributor The contributor to add.
     */
    fun addContributor(contributor: Person): EpisodeAtomBuilder

    /**
     * Adds a link to the list of links.
     *
     * @param link The link to add.
     */
    fun addLink(link: Link): EpisodeAtomBuilder
}
