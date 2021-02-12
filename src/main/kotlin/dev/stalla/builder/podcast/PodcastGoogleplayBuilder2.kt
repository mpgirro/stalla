package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.PodcastGoogleplay2
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/** Builder for constructing [PodcastGoogleplay2] instances. */
public interface PodcastGoogleplayBuilder2 : Builder<PodcastGoogleplay2> {

    /** Set the author value. */
    public fun author(author: String?): PodcastGoogleplayBuilder2

    /** Set the owner email value. */
    public fun owner(email: String?): PodcastGoogleplayBuilder2

    /** Adds an [ItunesStyleCategoryBuilder2] to the list of category builders. */
    public fun addCategoryBuilder(categoryBuilder: ItunesStyleCategoryBuilder2): PodcastGoogleplayBuilder2

    /** Adds multiple [ItunesStyleCategoryBuilder2] to the list of category builders. */
    public fun addCategoryBuilders(categoryBuilders: List<ItunesStyleCategoryBuilder2>): PodcastGoogleplayBuilder2 = apply {
        categoryBuilders.forEach(::addCategoryBuilder)
    }

    /** Set the description value. */
    public fun description(description: String?): PodcastGoogleplayBuilder2

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean?): PodcastGoogleplayBuilder2

    /** Set the block flag value. */
    public fun block(block: Boolean): PodcastGoogleplayBuilder2

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder2

    override fun from(model: PodcastGoogleplay2?): PodcastGoogleplayBuilder2 = whenNotNull(model) { googlePlay ->
        author(googlePlay.author)
        owner(googlePlay.owner)
        addCategoryBuilders(googlePlay.categories.asBuilders())
        description(googlePlay.description)
        explicit(googlePlay.explicit)
        block(googlePlay.block)
        imageBuilder(HrefOnlyImage.builder().from(googlePlay.image))
    }
}
