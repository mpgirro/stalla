package dev.stalla.builder.validating.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.builder.podcast.PodcastGoogleplayBuilder
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.util.anyNotNull

internal class ValidatingPodcastGoogleplayBuilder : PodcastGoogleplayBuilder {

    private var author: String? = null
    private var owner: String? = null
    private var categoryBuilders: MutableList<ItunesStyleCategoryBuilder> = mutableListOf()
    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean = false
    private var imageBuilder: HrefOnlyImageBuilder? = null

    override fun author(author: String?): PodcastGoogleplayBuilder = apply { this.author = author }

    override fun owner(email: String?): PodcastGoogleplayBuilder = apply { this.owner = email }

    override fun addCategoryBuilder(categoryBuilder: ItunesStyleCategoryBuilder): PodcastGoogleplayBuilder = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun description(description: String?): PodcastGoogleplayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): PodcastGoogleplayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastGoogleplayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder = apply { this.imageBuilder = imageBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() {
            if (block) return true
            if (anyNotNull(author, owner, description, explicit)) return true
            if (imageBuilder?.hasEnoughDataToBuild == true) return true
            return categoryBuilders.any { it.hasEnoughDataToBuild }
        }

    override fun build(): PodcastGoogleplay? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return PodcastGoogleplay(
            author = author,
            owner = owner,
            categories = categoryBuilders.mapNotNull { it.build() },
            description = description,
            explicit = explicit,
            block = block,
            image = imageBuilder?.build()
        )
    }
}