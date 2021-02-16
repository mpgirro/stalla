package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.util.whenNotNull

/** Builder for constructing [PodcastGoogleplay] instances. */
public interface PodcastGoogleplayBuilder : Builder<PodcastGoogleplay> {

    /** Set the author value. */
    public fun author(author: String?): PodcastGoogleplayBuilder

    /** Set the owner email value. */
    public fun owner(email: String?): PodcastGoogleplayBuilder

    /** Adds to the list of categories. */
    public fun addCategory(category: GoogleplayCategory): PodcastGoogleplayBuilder

    /** Adds multiple [GoogleplayCategory] to the list of categories. */
    public fun addCategories(categories: List<GoogleplayCategory>): PodcastGoogleplayBuilder = apply {
        categories.forEach(::addCategory)
    }

    /** Set the description value. */
    public fun description(description: String?): PodcastGoogleplayBuilder

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean?): PodcastGoogleplayBuilder

    /** Set the block flag value. */
    public fun block(block: Boolean): PodcastGoogleplayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder

    /** Set the new URL at which this feed is located. */
    public fun newFeedUrl(newFeedUrl: String?): PodcastGoogleplayBuilder

    override fun applyFrom(prototype: PodcastGoogleplay?): PodcastGoogleplayBuilder = whenNotNull(prototype) { googleplay ->
        author(googleplay.author)
        owner(googleplay.owner)
        addCategories(googleplay.categories)
        description(googleplay.description)
        explicit(googleplay.explicit)
        block(googleplay.block)
        imageBuilder(HrefOnlyImage.builder().applyFrom(googleplay.image))
        newFeedUrl(googleplay.newFeedUrl)
    }
}
