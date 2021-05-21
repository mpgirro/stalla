package dev.stalla.builder.validating.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.podcast.PodcastGoogleplayBuilder
import dev.stalla.model.googleplay.ExplicitType
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.util.InternalAPI
import dev.stalla.util.anyNotNull
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingPodcastGoogleplayBuilder : PodcastGoogleplayBuilder {

    private var author: String? = null
    private var email: String? = null
    private var categories: MutableList<GoogleplayCategory> = mutableListOf()
    private var description: String? = null
    private var explicitType: ExplicitType? = null
    private var block: Boolean = false
    private var imageBuilder: HrefOnlyImageBuilder? = null
    private var newFeedUrl: String? = null

    override fun author(author: String?): PodcastGoogleplayBuilder = apply { this.author = author }

    override fun email(email: String?): PodcastGoogleplayBuilder = apply { this.email = email }

    override fun addCategory(category: GoogleplayCategory): PodcastGoogleplayBuilder =
        apply { categories.add(category) }

    override fun description(description: String?): PodcastGoogleplayBuilder = apply { this.description = description }

    override fun explicitType(explicitType: ExplicitType?): PodcastGoogleplayBuilder = apply { this.explicitType = explicitType }

    override fun block(block: Boolean): PodcastGoogleplayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder =
        apply { this.imageBuilder = imageBuilder }

    override fun newFeedUrl(newFeedUrl: String?): PodcastGoogleplayBuilder = apply { this.newFeedUrl = newFeedUrl }

    override val hasEnoughDataToBuild: Boolean
        get() {
            if (block) return true
            if (anyNotNull(author, email, description, explicitType, newFeedUrl)) return true
            if (imageBuilder?.hasEnoughDataToBuild == true) return true
            return categories.isNotEmpty()
        }

    override fun build(): PodcastGoogleplay? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return PodcastGoogleplay(
            author = author,
            email = email,
            categories = categories.asUnmodifiable(),
            description = description,
            explicitType = explicitType,
            block = block,
            image = imageBuilder?.build(),
            newFeedUrl = newFeedUrl
        )
    }
}
