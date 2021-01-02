package io.hemin.wien.builder

import io.hemin.wien.model.Image
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.Podcast.ITunes.ShowType

/** Builder class for [Podcast.ITunes] instances. */
class PodcastITunesBuilder : Builder<Podcast.ITunes> {

    private lateinit var imageValue: Image
    private var explicit: Boolean? = null

    private var subtitle: String? = null
    private var summary: String? = null
    private var keywords: String? = null
    private var author: String? = null
    private var categories: MutableList<String> = mutableListOf()
    private var block: Boolean? = null
    private var complete: Boolean? = null
    private var type: ShowType? = null
    private var owner: Person? = null
    private var title: String? = null
    private var newFeedUrl: String? = null

    /** Set the subtitle value. */
    fun subtitle(subtitle: String?) = apply { this.subtitle = subtitle }

    /** Set the summary value. */
    fun summary(summary: String?) = apply { this.summary = summary }

    /** Set the Image. */
    fun image(image: Image) = apply { this.imageValue = image }

    /** Set the keywords value. */
    fun keywords(keywords: String?) = apply { this.keywords = keywords }

    /** Set the author value. */
    fun author(author: String?) = apply { this.author = author }

    /**
     * Adds a category to the list of categories.
     *
     * @param category The category to add.
     */
    fun addCategory(category: String) = apply {
        categories.add(category)
    }

    /** Set the explicit flag value. */
    fun explicit(explicit: Boolean) = apply { this.explicit = explicit }

    /** Set the block flag value. */
    fun block(block: Boolean?) = apply { this.block = block }

    /** Set the complete flag value. */
    fun complete(complete: Boolean?) = apply { this.complete = complete }

    /** Set the type value. */
    fun type(type: String?) = apply { this.type = ShowType.of(type) }

    /** Set the Person representing the owner. */
    fun owner(owner: Person?) = apply { this.owner = owner }

    /** Set the episode title. */
    fun title(title: String?) = apply {
        this.title = title
    }

    /** Set the new URL at which this feed is located. */
    fun newFeedUrl(newFeedUrl: String?) {
        this.newFeedUrl = newFeedUrl
    }

    override fun build(): Podcast.ITunes {
        require(::imageValue.isInitialized) { "The podcast itunes:image element is mandatory" }
        require(categories.isNotEmpty()) { "The podcast needs to have at least one itunes:category" }

        val explicitValue = explicit
        requireNotNull(explicitValue) { "The podcast itunes:explicit element is mandatory" }

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
