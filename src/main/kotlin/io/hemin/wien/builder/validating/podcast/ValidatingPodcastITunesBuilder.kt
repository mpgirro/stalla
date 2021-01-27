package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.podcast.PodcastITunesBuilder
import io.hemin.wien.model.Podcast

internal class ValidatingPodcastITunesBuilder : PodcastITunesBuilder {

    private lateinit var imageBuilderValue: HrefOnlyImageBuilder
    private var explicit: Boolean? = null

    private var subtitle: String? = null
    private var summary: String? = null
    private var keywords: String? = null
    private var author: String? = null
    private var categoryBuilders: MutableList<ITunesStyleCategoryBuilder> = mutableListOf()
    private var block: Boolean = false
    private var complete: Boolean = false
    private var type: Podcast.ITunes.ShowType? = null
    private var ownerBuilder: PersonBuilder? = null
    private var title: String? = null
    private var newFeedUrl: String? = null

    override fun subtitle(subtitle: String?): PodcastITunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): PodcastITunesBuilder = apply { this.summary = summary }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastITunesBuilder = apply { this.imageBuilderValue = imageBuilder }

    override fun keywords(keywords: String?): PodcastITunesBuilder = apply { this.keywords = keywords }

    override fun author(author: String?): PodcastITunesBuilder = apply { this.author = author }

    override fun addCategoryBuilder(categoryBuilder: ITunesStyleCategoryBuilder): PodcastITunesBuilder = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun explicit(explicit: Boolean): PodcastITunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastITunesBuilder = apply { this.block = block }

    override fun complete(complete: Boolean): PodcastITunesBuilder = apply { this.complete = complete }

    override fun type(type: String?): PodcastITunesBuilder = apply { this.type = Podcast.ITunes.ShowType.of(type) }

    override fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastITunesBuilder = apply { this.ownerBuilder = ownerBuilder }

    override fun title(title: String?): PodcastITunesBuilder = apply {
        this.title = title
    }

    override fun newFeedUrl(newFeedUrl: String?): PodcastITunesBuilder = apply {
        this.newFeedUrl = newFeedUrl
    }

    override val hasEnoughDataToBuild: Boolean
        get() = explicit != null && categoryBuilders.any { it.hasEnoughDataToBuild } &&
            (::imageBuilderValue.isInitialized && imageBuilderValue.hasEnoughDataToBuild)

    override fun build(): Podcast.ITunes? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Podcast.ITunes(
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
