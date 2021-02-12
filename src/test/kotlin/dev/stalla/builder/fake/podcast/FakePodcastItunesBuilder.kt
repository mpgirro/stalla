package dev.stalla.builder.fake.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastItunesBuilder
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType

internal class FakePodcastItunesBuilder : FakeBuilder<PodcastItunes>(), PodcastItunesBuilder {

    var imageBuilderValue: HrefOnlyImageBuilder? = null
    var explicit: Boolean? = null

    var subtitle: String? = null
    var summary: String? = null
    var keywords: String? = null
    var author: String? = null
    var categoryBuilders: MutableList<ItunesStyleCategoryBuilder> = mutableListOf()
    var block: Boolean? = null
    var complete: Boolean? = null
    var type: ShowType? = null
    var ownerBuilder: PersonBuilder? = null
    var title: String? = null
    var newFeedUrl: String? = null

    override fun subtitle(subtitle: String?): PodcastItunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): PodcastItunesBuilder = apply { this.summary = summary }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastItunesBuilder = apply { this.imageBuilderValue = imageBuilder }

    override fun keywords(keywords: String?): PodcastItunesBuilder = apply { this.keywords = keywords }

    override fun author(author: String?): PodcastItunesBuilder = apply { this.author = author }

    override fun addCategoryBuilder(categoryBuilder: ItunesStyleCategoryBuilder): PodcastItunesBuilder = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun explicit(explicit: Boolean): PodcastItunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastItunesBuilder = apply { this.block = block }

    override fun complete(complete: Boolean): PodcastItunesBuilder = apply { this.complete = complete }

    override fun type(type: String?): PodcastItunesBuilder = apply { this.type = ShowType.from(type) }

    override fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastItunesBuilder = apply { this.ownerBuilder = ownerBuilder }

    override fun title(title: String?): PodcastItunesBuilder = apply {
        this.title = title
    }

    override fun newFeedUrl(newFeedUrl: String?): PodcastItunesBuilder = apply {
        this.newFeedUrl = newFeedUrl
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastItunesBuilder) return false

        if (imageBuilderValue != other.imageBuilderValue) return false
        if (explicit != other.explicit) return false
        if (subtitle != other.subtitle) return false
        if (summary != other.summary) return false
        if (keywords != other.keywords) return false
        if (author != other.author) return false
        if (categoryBuilders != other.categoryBuilders) return false
        if (block != other.block) return false
        if (complete != other.complete) return false
        if (type != other.type) return false
        if (ownerBuilder != other.ownerBuilder) return false
        if (title != other.title) return false
        if (newFeedUrl != other.newFeedUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imageBuilderValue?.hashCode() ?: 0
        result = 31 * result + (explicit?.hashCode() ?: 0)
        result = 31 * result + (subtitle?.hashCode() ?: 0)
        result = 31 * result + (summary?.hashCode() ?: 0)
        result = 31 * result + (keywords?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + categoryBuilders.hashCode()
        result = 31 * result + (block?.hashCode() ?: 0)
        result = 31 * result + (complete?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (ownerBuilder?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (newFeedUrl?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakePodcastITunesBuilder(imageBuilderValue=$imageBuilderValue, explicit=$explicit, subtitle=$subtitle, summary=$summary, " +
            "keywords=$keywords, author=$author, categories=$categoryBuilders, block=$block, complete=$complete, type=$type, " +
            "ownerBuilder=$ownerBuilder, title=$title, newFeedUrl=$newFeedUrl)"
}
