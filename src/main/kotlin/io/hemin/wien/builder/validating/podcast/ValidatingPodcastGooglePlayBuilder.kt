package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.GooglePlay] instances. */
internal class ValidatingPodcastGooglePlayBuilder : PodcastGooglePlayBuilder {

    private var author: String? = null
    private var owner: String? = null
    private var categories: MutableList<String> = mutableListOf()
    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean? = null
    private var image: Image? = null

    /** Set the author value. */
    override fun author(author: String?): PodcastGooglePlayBuilder = apply { this.author = author }

    /** Set the owner email value. */
    override fun owner(email: String?): PodcastGooglePlayBuilder = apply { this.owner = email }

    /**
     * Adds a category to the list of categories.
     *
     * @param category The category to add.
     */
    override fun addCategory(category: String): PodcastGooglePlayBuilder = apply {
        categories.add(category)
    }

    /** Set the description value. */
    override fun description(description: String?): PodcastGooglePlayBuilder = apply { this.description = description }

    /** Set the explicit flag value. */
    override fun explicit(explicit: Boolean?): PodcastGooglePlayBuilder = apply { this.explicit = explicit }

    /** Set the block flag value. */
    override fun block(block: Boolean?): PodcastGooglePlayBuilder = apply { this.block = block }

    /** Set the Image. */
    override fun image(image: Image?): PodcastGooglePlayBuilder = apply { this.image = image }

    override fun build(): Podcast.GooglePlay? {
        if (categories.isEmpty() && allNull(author, owner, description, explicit, block, image)) {
            return null
        }

        return Podcast.GooglePlay(
            author = author,
            owner = owner,
            categories = immutableCopyOf(categories),
            description = description,
            explicit = explicit,
            block = block,
            image = image
        )
    }
}
