package io.hemin.wien.builder

import com.google.common.collect.ImmutableList
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person

class EpisodeAtomBuilder : Builder<Episode.Atom> {

    private var authors: MutableList<Person>      = mutableListOf()
    private var contributors: MutableList<Person> = mutableListOf()
    private var links: MutableList<Link>          = mutableListOf()

    fun addAuthor(author: Person?) = apply {
        author?.let { this.authors.add(it) }
    }

    fun addContributor(contributor: Person?) = apply {
        contributor?.let { this.contributors.add(it) }
    }

    fun addLink(link: Link?) = apply {
        link?.let { this.links.add(it) }
    }

    override fun build(): Episode.Atom? {
        val ats = if (authors.isEmpty()) null else Object()
        val cs = if (contributors.isEmpty()) null else Object()
        val ls = if (links.isEmpty()) null else Object()
        return if (Builder.anyNotNull(ats, cs, ls)) {
            Episode.Atom(
                authors      = ImmutableList.copyOf(authors),
                contributors = ImmutableList.copyOf(contributors),
                links        = ImmutableList.copyOf(links)
            )
        } else {
            null
        }
    }

}
