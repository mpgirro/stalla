package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.builder.PersonBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.Person
import dev.stalla.model.itunes.PodcastItunes2
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [PodcastItunes2] instances. */
public interface PodcastItunesBuilder2 : Builder<PodcastItunes2> {

    /** Set the subtitle value. */
    public fun subtitle(subtitle: String?): PodcastItunesBuilder2

    /** Set the summary value. */
    public fun summary(summary: String?): PodcastItunesBuilder2

    /** Set the ImageBuilder. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastItunesBuilder2

    /** Set the keywords value. */
    public fun keywords(keywords: String?): PodcastItunesBuilder2

    /** Set the author value. */
    public fun author(author: String?): PodcastItunesBuilder2

    /** Adds an [ItunesStyleCategoryBuilder2] to the list of category builders. */
    public fun addCategoryBuilder(categoryBuilder: ItunesStyleCategoryBuilder2): PodcastItunesBuilder2

    /** Adds multiple [ItunesStyleCategoryBuilder2] to the list of category builders. */
    public fun addCategoryBuilders(categoryBuilders: List<ItunesStyleCategoryBuilder2>): PodcastItunesBuilder2 = apply {
        categoryBuilders.forEach(::addCategoryBuilder)
    }

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean): PodcastItunesBuilder2

    /** Set the block flag value. */
    public fun block(block: Boolean): PodcastItunesBuilder2

    /** Set the complete flag value. */
    public fun complete(complete: Boolean): PodcastItunesBuilder2

    /** Set the type value. */
    public fun type(type: String?): PodcastItunesBuilder2

    /** Set the Person representing the owner. */
    public fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastItunesBuilder2

    /** Set the episode title. */
    public fun title(title: String?): PodcastItunesBuilder2

    /** Set the new URL at which this feed is located. */
    public fun newFeedUrl(newFeedUrl: String?): PodcastItunesBuilder2

    override fun from(model: PodcastItunes2?): PodcastItunesBuilder2 = whenNotNull(model) { itunes ->
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
