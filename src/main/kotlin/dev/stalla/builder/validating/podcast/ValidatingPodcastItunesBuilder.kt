package dev.stalla.builder.validating.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.podcast.PodcastItunesBuilder
import dev.stalla.builder.podcast.PodcastItunesOwnerBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.builder.validating.checkRequiredValue
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingPodcastItunesBuilder : PodcastItunesBuilder {

    private var imageBuilderValue: HrefOnlyImageBuilder? = null
    private var explicit: Boolean? = null

    private var subtitle: String? = null
    private var summary: String? = null
    private var keywords: String? = null
    private var author: String? = null
    private var categories: MutableList<ItunesCategory> = mutableListOf()
    private var block: Boolean = false
    private var complete: Boolean = false
    private var type: ShowType? = null
    private var ownerBuilder: PodcastItunesOwnerBuilder? = null
    private var title: String? = null
    private var newFeedUrl: String? = null

    override fun subtitle(subtitle: String?): PodcastItunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): PodcastItunesBuilder = apply { this.summary = summary }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastItunesBuilder =
        apply { this.imageBuilderValue = imageBuilder }

    override fun keywords(keywords: String?): PodcastItunesBuilder = apply { this.keywords = keywords }

    override fun author(author: String?): PodcastItunesBuilder = apply { this.author = author }

    override fun addCategory(category: ItunesCategory): PodcastItunesBuilder = apply { categories.add(category) }

    override fun explicit(explicit: Boolean): PodcastItunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastItunesBuilder = apply { this.block = block }

    override fun complete(complete: Boolean): PodcastItunesBuilder = apply { this.complete = complete }

    override fun type(type: String?): PodcastItunesBuilder = apply { this.type = ShowType.of(type) }

    override fun ownerBuilder(ownerBuilder: PodcastItunesOwnerBuilder?): PodcastItunesBuilder =
        apply { this.ownerBuilder = ownerBuilder }

    override fun title(title: String?): PodcastItunesBuilder = apply { this.title = title }

    override fun newFeedUrl(newFeedUrl: String?): PodcastItunesBuilder = apply { this.newFeedUrl = newFeedUrl }

    override val hasEnoughDataToBuild: Boolean
        get() = explicit != null && categories.isNotEmpty() &&
            imageBuilderValue?.hasEnoughDataToBuild == true

    override fun build(): PodcastItunes? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return PodcastItunes(
            subtitle = subtitle,
            summary = summary,
            image = checkRequiredValue(imageBuilderValue?.build(), "image is missing"),
            keywords = keywords,
            author = author,
            categories = categories.asUnmodifiable(),
            explicit = checkRequiredProperty(::explicit),
            block = block,
            complete = complete,
            type = type,
            owner = ownerBuilder?.build(),
            title = title,
            newFeedUrl = newFeedUrl
        )
    }

    @Suppress("ComplexMethod") // It's an equals
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastItunesBuilder) return false

        if (imageBuilderValue != other.imageBuilderValue) return false
        if (explicit != other.explicit) return false
        if (subtitle != other.subtitle) return false
        if (summary != other.summary) return false
        if (keywords != other.keywords) return false
        if (author != other.author) return false
        if (categories != other.categories) return false
        if (block != other.block) return false
        if (complete != other.complete) return false
        if (type != other.type) return false
        if (ownerBuilder != other.ownerBuilder) return false
        if (title != other.title) return false
        if (newFeedUrl != other.newFeedUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imageBuilderValue.hashCode()
        result = 31 * result + (explicit?.hashCode() ?: 0)
        result = 31 * result + (subtitle?.hashCode() ?: 0)
        result = 31 * result + (summary?.hashCode() ?: 0)
        result = 31 * result + (keywords?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + categories.hashCode()
        result = 31 * result + block.hashCode()
        result = 31 * result + complete.hashCode()
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (ownerBuilder?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (newFeedUrl?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String =
        "ValidatingPodcastItunesBuilder(imageBuilderValue=$imageBuilderValue, explicit=$explicit, subtitle=$subtitle, summary=$summary, " +
            "keywords=$keywords, author=$author, categories=$categories, block=$block, complete=$complete, type=$type, " +
            "ownerBuilder=$ownerBuilder, title=$title, newFeedUrl=$newFeedUrl)"
}
