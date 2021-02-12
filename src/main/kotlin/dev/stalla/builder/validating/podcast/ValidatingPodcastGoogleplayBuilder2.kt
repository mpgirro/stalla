package dev.stalla.builder.validating.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.builder.podcast.PodcastGoogleplayBuilder2
import dev.stalla.model.googleplay.PodcastGoogleplay2
import dev.stalla.util.anyNotNull

internal class ValidatingPodcastGoogleplayBuilder2 : PodcastGoogleplayBuilder2 {

    private var author: String? = null
    private var owner: String? = null
    private var categoryBuilders: MutableList<ItunesStyleCategoryBuilder2> = mutableListOf()
    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean = false
    private var imageBuilder: HrefOnlyImageBuilder? = null

    override fun author(author: String?): PodcastGoogleplayBuilder2 = apply { this.author = author }

    override fun owner(email: String?): PodcastGoogleplayBuilder2 = apply { this.owner = email }

    override fun addCategoryBuilder(categoryBuilder: ItunesStyleCategoryBuilder2): PodcastGoogleplayBuilder2 = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun description(description: String?): PodcastGoogleplayBuilder2 = apply { this.description = description }

    override fun explicit(explicit: Boolean?): PodcastGoogleplayBuilder2 = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastGoogleplayBuilder2 = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder2 = apply { this.imageBuilder = imageBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() {
            if (block) return true
            if (anyNotNull(author, owner, description, explicit)) return true
            if (imageBuilder?.hasEnoughDataToBuild == true) return true
            return categoryBuilders.any { it.hasEnoughDataToBuild }
        }

    override fun build(): PodcastGoogleplay2? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return PodcastGoogleplay2(
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
