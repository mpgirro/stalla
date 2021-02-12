package dev.stalla.builder.validating.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.podcast.PodcastItunesBuilder2
import dev.stalla.model.itunes.PodcastItunes2
import dev.stalla.model.itunes.ShowType

internal class ValidatingPodcastItunesBuilder2 : PodcastItunesBuilder2 {

    private lateinit var imageBuilderValue: HrefOnlyImageBuilder
    private var explicit: Boolean? = null

    private var subtitle: String? = null
    private var summary: String? = null
    private var keywords: String? = null
    private var author: String? = null
    private var categoryBuilders: MutableList<ItunesStyleCategoryBuilder2> = mutableListOf()
    private var block: Boolean = false
    private var complete: Boolean = false
    private var type: ShowType? = null
    private var ownerBuilder: PersonBuilder? = null
    private var title: String? = null
    private var newFeedUrl: String? = null

    override fun subtitle(subtitle: String?): PodcastItunesBuilder2 = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): PodcastItunesBuilder2 = apply { this.summary = summary }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastItunesBuilder2 = apply { this.imageBuilderValue = imageBuilder }

    override fun keywords(keywords: String?): PodcastItunesBuilder2 = apply { this.keywords = keywords }

    override fun author(author: String?): PodcastItunesBuilder2 = apply { this.author = author }

    override fun addCategoryBuilder(categoryBuilder: ItunesStyleCategoryBuilder2): PodcastItunesBuilder2 = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun explicit(explicit: Boolean): PodcastItunesBuilder2 = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastItunesBuilder2 = apply { this.block = block }

    override fun complete(complete: Boolean): PodcastItunesBuilder2 = apply { this.complete = complete }

    override fun type(type: String?): PodcastItunesBuilder2 = apply { this.type = ShowType.from(type) }

    override fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastItunesBuilder2 = apply { this.ownerBuilder = ownerBuilder }

    override fun title(title: String?): PodcastItunesBuilder2 = apply {
        this.title = title
    }

    override fun newFeedUrl(newFeedUrl: String?): PodcastItunesBuilder2 = apply {
        this.newFeedUrl = newFeedUrl
    }

    override val hasEnoughDataToBuild: Boolean
        get() = explicit != null && categoryBuilders.any { it.hasEnoughDataToBuild } &&
            (::imageBuilderValue.isInitialized && imageBuilderValue.hasEnoughDataToBuild)

    override fun build(): PodcastItunes2? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return PodcastItunes2(
            subtitle = subtitle,
            summary = summary,
            image = imageBuilderValue.build() ?: return null,
            keywords = keywords,
            author = author,
            categories = categoryBuilders.mapNotNull { it.build() },
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
