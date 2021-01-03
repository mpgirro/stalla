package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast

internal interface PodcastAtomBuilder : Builder<Podcast.Atom> {

    /**
     * Adds a person to the list of authors.
     *
     * @param author The author to add.
     */
    fun addAuthor(author: Person): PodcastAtomBuilder

    /**
     * Adds a person to the list of contributors.
     *
     * @param contributor The contributor to add.
     */
    fun addContributor(contributor: Person): PodcastAtomBuilder

    /**
     * Adds a link to the list of links.
     *
     * @param link The link to add.
     */
    fun addLink(link: Link): PodcastAtomBuilder
}
