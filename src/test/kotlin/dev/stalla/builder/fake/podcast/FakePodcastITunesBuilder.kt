package dev.stalla.builder.fake.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ITunesStyleCategoryBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastITunesBuilder
import dev.stalla.model.Podcast

internal class FakePodcastITunesBuilder : FakeBuilder<Podcast.ITunes>(), PodcastITunesBuilder {

    var imageBuilderValue: HrefOnlyImageBuilder? = null
    var explicit: Boolean? = null

    var subtitle: String? = null
    var summary: String? = null
    var keywords: String? = null
    var author: String? = null
    var categoryBuilders: MutableList<ITunesStyleCategoryBuilder> = mutableListOf()
    var block: Boolean? = null
    var complete: Boolean? = null
    var type: Podcast.ITunes.ShowType? = null
    var ownerBuilder: PersonBuilder? = null
    var title: String? = null
    var newFeedUrl: String? = null

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

    override fun type(type: String?): PodcastITunesBuilder = apply { this.type = Podcast.ITunes.ShowType.from(type) }

    override fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastITunesBuilder = apply { this.ownerBuilder = ownerBuilder }

    override fun title(title: String?): PodcastITunesBuilder = apply {
        this.title = title
    }

    override fun newFeedUrl(newFeedUrl: String?): PodcastITunesBuilder = apply {
        this.newFeedUrl = newFeedUrl
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastITunesBuilder) return false

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
