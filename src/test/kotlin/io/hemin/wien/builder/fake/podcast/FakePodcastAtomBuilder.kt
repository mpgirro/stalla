package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastAtomBuilder : FakeBuilder<Podcast.Atom>(), PodcastAtomBuilder {

    var authors: MutableList<Person> = mutableListOf()
    var contributors: MutableList<Person> = mutableListOf()
    var links: MutableList<Link> = mutableListOf()

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
}
