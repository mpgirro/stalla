package dev.stalla.builder.validating.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.podcast.PodcastItunesBuilder
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingPodcastItunesBuilder : PodcastItunesBuilder {

    private lateinit var imageBuilderValue: HrefOnlyImageBuilder
    private var explicit: Boolean? = null

    private var subtitle: String? = null
    private var summary: String? = null
    private var keywords: String? = null
    private var author: String? = null
    private var categories: MutableList<ItunesCategory> = mutableListOf()
    private var block: Boolean = false
    private var complete: Boolean = false
    private var type: ShowType? = null
    private var ownerBuilder: PersonBuilder? = null
    private var title: String? = null
    private var newFeedUrl: String? = null

    override fun subtitle(subtitle: String?): PodcastItunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): PodcastItunesBuilder = apply { this.summary = summary }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastItunesBuilder = apply { this.imageBuilderValue = imageBuilder }

    override fun keywords(keywords: String?): PodcastItunesBuilder = apply { this.keywords = keywords }

    override fun author(author: String?): PodcastItunesBuilder = apply { this.author = author }

    override fun addCategory(category: ItunesCategory): PodcastItunesBuilder = apply { categories.add(category) }

    override fun explicit(explicit: Boolean): PodcastItunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastItunesBuilder = apply { this.block = block }

    override fun complete(complete: Boolean): PodcastItunesBuilder = apply { this.complete = complete }

    override fun type(type: String?): PodcastItunesBuilder = apply { this.type = ShowType.of(type) }

    override fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastItunesBuilder = apply { this.ownerBuilder = ownerBuilder }

    override fun title(title: String?): PodcastItunesBuilder = apply {
        this.title = title
    }

    override fun newFeedUrl(newFeedUrl: String?): PodcastItunesBuilder = apply {
        this.newFeedUrl = newFeedUrl
    }

    override val hasEnoughDataToBuild: Boolean
        get() = explicit != null && categories.isNotEmpty() &&
            (::imageBuilderValue.isInitialized && imageBuilderValue.hasEnoughDataToBuild)

    override fun build(): PodcastItunes? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return PodcastItunes(
            subtitle = subtitle,
            summary = summary,
            image = imageBuilderValue.build() ?: return null,
            keywords = keywords,
            author = author,
            categories = categories,
            explicit = explicit ?: throw IllegalStateException("The explicit flag is not set, while hasEnoughDataToBuild == true"),
            block = block,
            complete = complete,
            type = type,
            owner = ownerBuilder?.build(),
            title = title,
            newFeedUrl = newFeedUrl
        )
    }
}
