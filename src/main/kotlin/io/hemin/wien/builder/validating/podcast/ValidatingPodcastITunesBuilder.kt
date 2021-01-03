package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastITunesBuilder
import io.hemin.wien.model.Image
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.ITunes] instances. */
internal class ValidatingPodcastITunesBuilder : PodcastITunesBuilder {

    private lateinit var imageValue: Image
    private var explicit: Boolean? = null

    private var subtitle: String? = null
    private var summary: String? = null
    private var keywords: String? = null
    private var author: String? = null
    private var categories: MutableList<String> = mutableListOf()
    private var block: Boolean? = null
    private var complete: Boolean? = null
    private var type: Podcast.ITunes.ShowType? = null
    private var owner: Person? = null
    private var title: String? = null
    private var newFeedUrl: String? = null

    /** Set the subtitle value. */
    override fun subtitle(subtitle: String?): PodcastITunesBuilder = apply { this.subtitle = subtitle }

    /** Set the summary value. */
    override fun summary(summary: String?): PodcastITunesBuilder = apply { this.summary = summary }

    /** Set the Image. */
    override fun image(image: Image): PodcastITunesBuilder = apply { this.imageValue = image }

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

    override fun build(): Podcast.ITunes? {
        val explicitValue = explicit
        if (!::imageValue.isInitialized || categories.isNotEmpty() || explicitValue == null) {
            return null
        }

        return Podcast.ITunes(
            subtitle = subtitle,
            summary = summary,
            image = imageValue,
            keywords = keywords,
            author = author,
            categories = immutableCopyOf(categories),
            explicit = explicitValue,
            block = block,
            complete = complete,
            type = type,
            owner = owner,
            title = title,
            newFeedUrl = newFeedUrl
        )
    }
}
