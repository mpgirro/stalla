@file:Suppress("TooManyFunctions")

package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.Person
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.util.whenNotNull

/** Builder for constructing [PodcastItunes] instances. */
public interface PodcastItunesBuilder : Builder<PodcastItunes> {

    /** Set the subtitle value. */
    public fun subtitle(subtitle: String?): PodcastItunesBuilder

    /** Set the summary value. */
    public fun summary(summary: String?): PodcastItunesBuilder

    /** Set the ImageBuilder. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastItunesBuilder

    /** Set the keywords value. */
    public fun keywords(keywords: String?): PodcastItunesBuilder

    /** Set the author value. */
    public fun author(author: String?): PodcastItunesBuilder

    /** Adds the [ItunesCategory] to the list of categories. */
    public fun addCategory(category: ItunesCategory): PodcastItunesBuilder

    /** Adds all of the [ItunesCategory] to the list of categories. */
    public fun addAllCategory(categories: List<ItunesCategory>): PodcastItunesBuilder =
        apply { categories.forEach(::addCategory) }

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean): PodcastItunesBuilder

    /** Set the block flag value. */
    public fun block(block: Boolean): PodcastItunesBuilder

    /** Set the complete flag value. */
    public fun complete(complete: Boolean): PodcastItunesBuilder

    /** Set the type value. */
    public fun type(type: String?): PodcastItunesBuilder

    /** Set the Person representing the owner. */
    public fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastItunesBuilder

    /** Set the episode title. */
    public fun title(title: String?): PodcastItunesBuilder

    /** Set the new URL at which this feed is located. */
    public fun newFeedUrl(newFeedUrl: String?): PodcastItunesBuilder

    override fun applyFrom(prototype: PodcastItunes?): PodcastItunesBuilder = whenNotNull(prototype) { itunes ->
        subtitle(itunes.subtitle)
        summary(itunes.summary)
        imageBuilder(HrefOnlyImage.builder().applyFrom(itunes.image))
        keywords(itunes.keywords)
        author(itunes.author)
        addAllCategory(itunes.categories)
        explicit(itunes.explicit)
        block(itunes.block)
        complete(itunes.complete)
        type(itunes.type?.type)
        ownerBuilder(Person.builder().applyFrom(itunes.owner))
        title(itunes.title)
        newFeedUrl(itunes.newFeedUrl)
    }
}
