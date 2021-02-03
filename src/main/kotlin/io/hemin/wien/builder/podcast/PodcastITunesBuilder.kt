package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.model.ITunesStyleCategory
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Podcast.ITunes] instances. */
interface PodcastITunesBuilder : Builder<Podcast.ITunes> {

    /** Set the subtitle value. */
    fun subtitle(subtitle: String?): PodcastITunesBuilder

    /** Set the summary value. */
    fun summary(summary: String?): PodcastITunesBuilder

    /** Set the ImageBuilder. */
    fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastITunesBuilder

    /** Set the keywords value. */
    fun keywords(keywords: String?): PodcastITunesBuilder

    /** Set the author value. */
    fun author(author: String?): PodcastITunesBuilder

    /** Adds an [ITunesStyleCategoryBuilder] to the list of category builders. */
    fun addCategoryBuilder(categoryBuilder: ITunesStyleCategoryBuilder): PodcastITunesBuilder

    /** Adds multiple [ITunesStyleCategoryBuilder] to the list of category builders. */
    fun addCategoryBuilders(categoryBuilders: List<ITunesStyleCategoryBuilder>): PodcastITunesBuilder = apply {
        categoryBuilders.forEach { categoryBuilder -> addCategoryBuilder(categoryBuilder) }
    }

    /** Set the explicit flag value. */
    fun explicit(explicit: Boolean): PodcastITunesBuilder

    /** Set the block flag value. */
    fun block(block: Boolean): PodcastITunesBuilder

    /** Set the complete flag value. */
    fun complete(complete: Boolean): PodcastITunesBuilder

    /** Set the type value. */
    fun type(type: String?): PodcastITunesBuilder

    /** Set the Person representing the owner. */
    fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastITunesBuilder

    /** Set the episode title. */
    fun title(title: String?): PodcastITunesBuilder

    /** Set the new URL at which this feed is located. */
    fun newFeedUrl(newFeedUrl: String?): PodcastITunesBuilder

    override fun from(model: Podcast.ITunes?): PodcastITunesBuilder = whenNotNull(model) { itunes ->
        subtitle(itunes.subtitle)
        summary(itunes.summary)
        imageBuilder(HrefOnlyImage.builder().from(itunes.image))
        keywords(itunes.keywords)
        author(itunes.author)
        addCategoryBuilders(itunes.categories.map { category -> ITunesStyleCategory.builder().from(category) })
        explicit(itunes.explicit)
        block(itunes.block)
        complete(itunes.complete)
        type(itunes.type?.type)
        ownerBuilder(Person.builder().from(itunes.owner))
        title(itunes.title)
        newFeedUrl(itunes.newFeedUrl)
    }
}
