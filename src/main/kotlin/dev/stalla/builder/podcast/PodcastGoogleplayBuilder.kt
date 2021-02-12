package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [PodcastGoogleplay] instances. */
public interface PodcastGoogleplayBuilder : Builder<PodcastGoogleplay> {

    /** Set the author value. */
    public fun author(author: String?): PodcastGoogleplayBuilder

    /** Set the owner email value. */
    public fun owner(email: String?): PodcastGoogleplayBuilder

    /** Adds an [ItunesStyleCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilder(categoryBuilder: ItunesStyleCategoryBuilder): PodcastGoogleplayBuilder

    /** Adds multiple [ItunesStyleCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilders(categoryBuilders: List<ItunesStyleCategoryBuilder>): PodcastGoogleplayBuilder = apply {
        categoryBuilders.forEach(::addCategoryBuilder)
    }

    /** Set the description value. */
    public fun description(description: String?): PodcastGoogleplayBuilder

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean?): PodcastGoogleplayBuilder

    /** Set the block flag value. */
    public fun block(block: Boolean): PodcastGoogleplayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder

    override fun from(model: PodcastGoogleplay?): PodcastGoogleplayBuilder = whenNotNull(model) { googlePlay ->
        author(googlePlay.author)
        owner(googlePlay.owner)
        addCategoryBuilders(googlePlay.categories.asBuilders())
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
