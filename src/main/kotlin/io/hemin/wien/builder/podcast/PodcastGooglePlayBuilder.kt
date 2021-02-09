package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.asBuilders
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Podcast.GooglePlay] instances. */
public interface PodcastGooglePlayBuilder : Builder<Podcast.GooglePlay> {

    /** Set the author value. */
    public fun author(author: String?): PodcastGooglePlayBuilder

    /** Set the owner email value. */
    public fun owner(email: String?): PodcastGooglePlayBuilder

    /** Adds an [ITunesStyleCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilder(categoryBuilder: ITunesStyleCategoryBuilder): PodcastGooglePlayBuilder

    /** Adds multiple [ITunesStyleCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilders(categoryBuilders: List<ITunesStyleCategoryBuilder>): PodcastGooglePlayBuilder = apply {
        categoryBuilders.forEach(::addCategoryBuilder)
    }

    /** Set the description value. */
    public fun description(description: String?): PodcastGooglePlayBuilder

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean?): PodcastGooglePlayBuilder

    /** Set the block flag value. */
    public fun block(block: Boolean): PodcastGooglePlayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGooglePlayBuilder

    override fun from(model: Podcast.GooglePlay?): PodcastGooglePlayBuilder = whenNotNull(model) { googlePlay ->
        author(googlePlay.author)
        owner(googlePlay.owner)
        addCategoryBuilders(googlePlay.categories.asBuilders())
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
