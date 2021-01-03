package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.model.Podcast

internal class ValidatingPodcastAtomBuilder : PodcastAtomBuilder {

    private var authorBuilders: MutableList<PersonBuilder> = mutableListOf()
    private var contributorBuilders: MutableList<PersonBuilder> = mutableListOf()
    private var linkBuilders: MutableList<LinkBuilder> = mutableListOf()

    override fun addAuthorBuilder(authorBuilder: PersonBuilder): PodcastAtomBuilder = apply {
        authorBuilders.add(authorBuilder)
    }

    override fun addContributorBuilder(contributorBuilder: PersonBuilder): PodcastAtomBuilder = apply {
        contributorBuilders.add(contributorBuilder)
    }

    override fun addLinkBuilder(linkBuilder: LinkBuilder): PodcastAtomBuilder = apply {
        linkBuilders.add(linkBuilder)
    }

    override fun build(): Podcast.Atom? {
        val authors = authorBuilders.mapNotNull { it.build() }
        val contributors = contributorBuilders.mapNotNull { it.build() }
        val links = linkBuilders.mapNotNull { it.build() }

        if (authors.isEmpty() && contributors.isEmpty() && links.isEmpty()) {
            return null
        }

        return Podcast.Atom(authors, contributors, links)
    }
}
