package io.hemin.wien.builder

import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.Atom] instances. */
class PodcastAtomBuilder : Builder<Podcast.Atom> {

    private var authors: MutableList<Person> = mutableListOf()
    private var contributors: MutableList<Person> = mutableListOf()
    private var links: MutableList<Link> = mutableListOf()

    /**
     * Adds a person to the list of authors.
     *
     * @param author The author to add.
     */
    fun addAuthor(author: Person?) = apply {
        author?.let { this.authors.add(it) }
    }

    /**
     * Adds a person to the list of contributors.
     *
     * @param author The author to add.
     */
    fun addContributor(contributor: Person?) = apply {
        contributor?.let { this.contributors.add(it) }
    }

    /**
     * Adds a link to the list of links.
     *
     * @param link The link to add.
     */
    fun addLink(link: Link?) = apply {
        link?.let { this.links.add(it) }
    }

    override fun build(): Podcast.Atom? {
        val oAuthors = if (authors.isEmpty()) null else Object()
        val oContributors = if (contributors.isEmpty()) null else Object()
        val oLinks = if (links.isEmpty()) null else Object()

        return if (anyNotNull(oAuthors, oContributors, oLinks)) {
            Podcast.Atom(
                authors = immutableCopyOf(authors),
                contributors = immutableCopyOf(contributors),
                links = immutableCopyOf(links)
            )
        } else {
            null
        }
    }
}
