package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.asBuilders
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Podcast.ITunes] instances. */
public interface PodcastITunesBuilder : Builder<Podcast.ITunes> {

    /** Set the subtitle value. */
    public fun subtitle(subtitle: String?): PodcastITunesBuilder

    /** Set the summary value. */
    public fun summary(summary: String?): PodcastITunesBuilder

    /** Set the ImageBuilder. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastITunesBuilder

    /** Set the keywords value. */
    public fun keywords(keywords: String?): PodcastITunesBuilder

    /** Set the author value. */
    public fun author(author: String?): PodcastITunesBuilder

    /** Adds an [ITunesStyleCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilder(categoryBuilder: ITunesStyleCategoryBuilder): PodcastITunesBuilder

    /** Adds multiple [ITunesStyleCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilders(categoryBuilders: List<ITunesStyleCategoryBuilder>): PodcastITunesBuilder = apply {
        categoryBuilders.forEach(::addCategoryBuilder)
    }

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean): PodcastITunesBuilder

    /** Set the block flag value. */
    public fun block(block: Boolean): PodcastITunesBuilder

    /** Set the complete flag value. */
    public fun complete(complete: Boolean): PodcastITunesBuilder

    /** Set the type value. */
    public fun type(type: String?): PodcastITunesBuilder

    /** Set the Person representing the owner. */
    public fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastITunesBuilder

    /** Set the episode title. */
    public fun title(title: String?): PodcastITunesBuilder

    /** Set the new URL at which this feed is located. */
    public fun newFeedUrl(newFeedUrl: String?): PodcastITunesBuilder

    override fun from(model: Podcast.ITunes?): PodcastITunesBuilder = whenNotNull(model) { itunes ->
        subtitle(itunes.subtitle)
        summary(itunes.summary)
        imageBuilder(HrefOnlyImage.builder().from(itunes.image))
        keywords(itunes.keywords)
        author(itunes.author)
        addCategoryBuilders(itunes.categories.asBuilders())
        explicit(itunes.explicit)
        block(itunes.block)
        complete(itunes.complete)
        type(itunes.type?.type)
        ownerBuilder(Person.builder().from(itunes.owner))
        title(itunes.title)
        newFeedUrl(itunes.newFeedUrl)
    }
}
