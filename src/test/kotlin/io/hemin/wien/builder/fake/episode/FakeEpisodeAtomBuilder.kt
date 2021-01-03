package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeAtomBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeAtomBuilder : FakeBuilder<Episode.Atom>(), EpisodeAtomBuilder {

    var authors: MutableList<Person> = mutableListOf()
    var contributors: MutableList<Person> = mutableListOf()
    var links: MutableList<Link> = mutableListOf()

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
}
