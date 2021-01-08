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

    override val hasEnoughDataToBuild: Boolean
        get() = (authorBuilders + contributorBuilders + linkBuilders).any { it.hasEnoughDataToBuild }

    override fun build(): Episode.Atom? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val authors = authorBuilders.mapNotNull { it.build() }
        val contributors = contributorBuilders.mapNotNull { it.build() }
        val links = linkBuilders.mapNotNull { it.build() }
        return Episode.Atom(authors, contributors, links)
    }
}
