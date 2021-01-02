package io.hemin.wien.builder

import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.GooglePlay] instances. */
class PodcastGooglePlayBuilder : Builder<Podcast.GooglePlay> {

    private var author: String? = null
    private var owner: String? = null
    private var categories: MutableList<String> = mutableListOf()
    private var description: String? = null
    private var explicit: Boolean? = null
    private var block: Boolean? = null
    private var image: Image? = null

    /** Set the author value. */
    fun author(author: String?) = apply { this.author = author }

    /** Set the owner email value. */
    fun owner(email: String?) = apply { this.owner = email }

    /**
     * Adds a category to the list of categories.
     *
     * @param category The category to add.
     */
    fun addCategory(category: String) = apply {
        categories.add(category)
    }

    /** Set the description value. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the explicit flag value. */
    fun explicit(explicit: Boolean?) = apply { this.explicit = explicit }

    /** Set the block flag value. */
    fun block(block: Boolean?) = apply { this.block = block }

    /** Set the Image. */
    fun image(image: Image?) = apply { this.image = image }

    override fun build(): Podcast.GooglePlay? {
        val oCategories = if (categories.isEmpty()) null else Object()
        return if (anyNotNull(author, owner, oCategories, description, explicit, block, image)) {
            Podcast.GooglePlay(
                author = author,
                owner = owner,
                categories = immutableCopyOf(categories),
                description = description,
                explicit = explicit,
                block = block,
                image = image
            )
        } else {
            null
        }
    }
}
