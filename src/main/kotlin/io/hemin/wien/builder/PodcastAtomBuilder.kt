package io.hemin.wien.builder

import com.google.common.collect.ImmutableList
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.Atom] instances. */
class PodcastAtomBuilder : Builder<Podcast.Atom> {

    private var authors: MutableList<Person>      = mutableListOf()
    private var contributors: MutableList<Person> = mutableListOf()
    private var links: MutableList<Link>          = mutableListOf()

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
        val immutableAuthors = immutableCopyOf(authors)
        val immutableContributors = immutableCopyOf(contributors)
        val immutableLinks = immutableCopyOf(links)

        val oAuthors = if (immutableAuthors.isEmpty()) null else Object()
        val oContributors = if (immutableContributors.isEmpty()) null else Object()
        val oLinks = if (immutableLinks.isEmpty()) null else Object()

        return if (anyNotNull(oAuthors, oContributors, oLinks)) {
            Podcast.Atom(
                authors      = immutableAuthors,
                contributors = immutableContributors,
                links        = immutableLinks
            )
        } else {
            null
        }
    }

}
