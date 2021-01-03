package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeAtomBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person

/** Builder class for [Episode.Atom] instances. */
internal class ValidatingEpisodeAtomBuilder : EpisodeAtomBuilder {

    private var authors: MutableList<Person> = mutableListOf()
    private var contributors: MutableList<Person> = mutableListOf()
    private var links: MutableList<Link> = mutableListOf()

    /**
     * Adds a person to the list of authors.
     *
     * @param author The author to add.
     */
    override fun addAuthor(author: Person): EpisodeAtomBuilder = apply {
        authors.add(author)
    }

    /**
     * Adds a person to the list of contributors.
     *
     * @param contributor The contributor to add.
     */
    override fun addContributor(contributor: Person): EpisodeAtomBuilder = apply {
        contributors.add(contributor)
    }

    /**
     * Adds a link to the list of links.
     *
     * @param link The link to add.
     */
    override fun addLink(link: Link): EpisodeAtomBuilder = apply {
        links.add(link)
    }

    override fun build(): Episode.Atom? {
        if (authors.isEmpty() && contributors.isEmpty() && links.isEmpty()) {
            return null
        }

        return Episode.Atom(
            authors = immutableCopyOf(authors),
            contributors = immutableCopyOf(contributors),
            links = immutableCopyOf(links)
        )
    }
}
