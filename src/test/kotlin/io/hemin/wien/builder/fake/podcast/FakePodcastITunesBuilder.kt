package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastITunesBuilder
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastITunesBuilder : FakeBuilder<Podcast.ITunes>(), PodcastITunesBuilder {

    var imageBuilderValue: ImageBuilder? = null
    var explicit: Boolean? = null

    var subtitle: String? = null
    var summary: String? = null
    var keywords: String? = null
    var author: String? = null
    var categories: MutableList<String> = mutableListOf()
    var block: Boolean? = null
    var complete: Boolean? = null
    var type: Podcast.ITunes.ShowType? = null
    var ownerBuilder: PersonBuilder? = null
    var title: String? = null
    var newFeedUrl: String? = null

    override fun subtitle(subtitle: String?): PodcastITunesBuilder = apply { this.subtitle = subtitle }

    override fun summary(summary: String?): PodcastITunesBuilder = apply { this.summary = summary }

    override fun imageBuilder(imageBuilder: ImageBuilder): PodcastITunesBuilder = apply { this.imageBuilderValue = imageBuilder }

    override fun keywords(keywords: String?): PodcastITunesBuilder = apply { this.keywords = keywords }

    override fun author(author: String?): PodcastITunesBuilder = apply { this.author = author }

    override fun addCategory(category: String): PodcastITunesBuilder = apply {
        categories.add(category)
    }

    override fun explicit(explicit: Boolean): PodcastITunesBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): PodcastITunesBuilder = apply { this.block = block }

    override fun complete(complete: Boolean?): PodcastITunesBuilder = apply { this.complete = complete }

    override fun type(type: String?): PodcastITunesBuilder = apply { this.type = Podcast.ITunes.ShowType.of(type) }

    override fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastITunesBuilder = apply { this.ownerBuilder = ownerBuilder }

    override fun title(title: String?): PodcastITunesBuilder = apply {
        this.title = title
    }

    override fun newFeedUrl(newFeedUrl: String?): PodcastITunesBuilder = apply {
        this.newFeedUrl = newFeedUrl
    }
}
