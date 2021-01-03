package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastITunesBuilder
import io.hemin.wien.model.Image
import io.hemin.wien.model.Person
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
    var owner: Person? = null
    var title: String? = null
    var newFeedUrl: String? = null

    /** Set the subtitle value. */
    override fun subtitle(subtitle: String?): PodcastITunesBuilder = apply { this.subtitle = subtitle }

    /** Set the summary value. */
    override fun summary(summary: String?): PodcastITunesBuilder = apply { this.summary = summary }

    /** Set the Image. */
    override fun imageBuilder(imageBuilder: ImageBuilder): PodcastITunesBuilder = apply { this.imageBuilderValue = imageBuilder }

    /** Set the keywords value. */
    override fun keywords(keywords: String?): PodcastITunesBuilder = apply { this.keywords = keywords }

    /** Set the author value. */
    override fun author(author: String?): PodcastITunesBuilder = apply { this.author = author }

    /**
     * Adds a category to the list of categories.
     *
     * @param category The category to add.
     */
    override fun addCategory(category: String): PodcastITunesBuilder = apply {
        categories.add(category)
    }

    /** Set the explicit flag value. */
    override fun explicit(explicit: Boolean): PodcastITunesBuilder = apply { this.explicit = explicit }

    /** Set the block flag value. */
    override fun block(block: Boolean?): PodcastITunesBuilder = apply { this.block = block }

    /** Set the complete flag value. */
    override fun complete(complete: Boolean?): PodcastITunesBuilder = apply { this.complete = complete }

    /** Set the type value. */
    override fun type(type: String?): PodcastITunesBuilder = apply { this.type = Podcast.ITunes.ShowType.of(type) }

    /** Set the Person representing the owner. */
    override fun owner(owner: Person?): PodcastITunesBuilder = apply { this.owner = owner }

    /** Set the episode title. */
    override fun title(title: String?): PodcastITunesBuilder = apply {
        this.title = title
    }

    /** Set the new URL at which this feed is located. */
    override fun newFeedUrl(newFeedUrl: String?): PodcastITunesBuilder = apply {
        this.newFeedUrl = newFeedUrl
    }
}
