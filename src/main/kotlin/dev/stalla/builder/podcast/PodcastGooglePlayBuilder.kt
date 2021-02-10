package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ITunesStyleCategoryBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [PodcastGoogleplay] instances. */
public interface PodcastGooglePlayBuilder : Builder<PodcastGoogleplay> {

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

    override fun from(model: PodcastGoogleplay?): PodcastGooglePlayBuilder = whenNotNull(model) { googlePlay ->
        author(googlePlay.author)
        owner(googlePlay.owner)
        addCategoryBuilders(googlePlay.categories.asBuilders())
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
