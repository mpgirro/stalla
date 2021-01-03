package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.Atom] instances. */
internal class ValidatingPodcastAtomBuilder : PodcastAtomBuilder {

    private var authors: MutableList<Person> = mutableListOf()
    private var contributors: MutableList<Person> = mutableListOf()
    private var links: MutableList<Link> = mutableListOf()

    /**
     * Adds a person to the list of authors.
     *
     * @param author The author to add.
     */
    override fun addAuthor(author: Person): PodcastAtomBuilder = apply {
        authors.add(author)
    }

    /**
     * Adds a person to the list of contributors.
     *
     * @param contributor The contributor to add.
     */
    override fun addContributor(contributor: Person): PodcastAtomBuilder = apply {
        contributors.add(contributor)
    }

    /**
     * Adds a link to the list of links.
     *
     * @param link The link to add.
     */
    override fun addLink(link: Link): PodcastAtomBuilder = apply {
        links.add(link)
    }

    override fun build(): Podcast.Atom? {
        if (authors.isEmpty() && contributors.isEmpty() && links.isEmpty()) {
            return null
        }

        return Podcast.Atom(
            authors = immutableCopyOf(authors),
            contributors = immutableCopyOf(contributors),
            links = immutableCopyOf(links)
        )
    }
}
