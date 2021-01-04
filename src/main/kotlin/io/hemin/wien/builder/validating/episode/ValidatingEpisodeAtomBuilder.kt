package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeAtomBuilder
import io.hemin.wien.model.Episode

internal class ValidatingEpisodeAtomBuilder : EpisodeAtomBuilder {

    private var authorBuilders: MutableList<PersonBuilder> = mutableListOf()
    private var contributorBuilders: MutableList<PersonBuilder> = mutableListOf()
    private var linkBuilders: MutableList<LinkBuilder> = mutableListOf()

    override fun addAuthorBuilder(authorBuilder: PersonBuilder): EpisodeAtomBuilder = apply {
        authorBuilders.add(authorBuilder)
    }

    override fun addContributorBuilder(contributorBuilder: PersonBuilder): EpisodeAtomBuilder = apply {
        contributorBuilders.add(contributorBuilder)
    }

    override fun addLinkBuilder(linkBuilder: LinkBuilder): EpisodeAtomBuilder = apply {
        linkBuilders.add(linkBuilder)
    }

    override fun build(): Episode.Atom? {
        val authors = authorBuilders.mapNotNull { it.build() }
        val contributors = contributorBuilders.mapNotNull { it.build() }
        val links = linkBuilders.mapNotNull { it.build() }

        if (authors.isEmpty() && contributors.isEmpty() && links.isEmpty()) {
            return null
        }
        return Episode.Atom(authors, contributors, links)
    }
}
