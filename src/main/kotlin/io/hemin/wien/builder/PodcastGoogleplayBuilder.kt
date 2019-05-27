package io.hemin.wien.builder

import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast

/** Builder class for [Podcast.Googleplay] instances. */
class PodcastGoogleplayBuilder : Builder<Podcast.Googleplay> {

    private var author: String?                 = null
    private var categories: MutableList<String> = mutableListOf()
    private var description: String?            = null
    private var explicit: Boolean?              = null
    private var image: Image?                   = null

    /** Set the author value. */
    fun author(author: String?) = apply { this.author = author }

    /**
     * Adds a category to the list of categories.
     *
     * @param category The category to add.
     */
    fun addCategory(category: String?) = apply {
        category?.let { this.categories.add(it) }
    }

    /** Set the description value. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the explicit flag value. */
    fun explicit(explicit: Boolean?) = apply { this.explicit = explicit }

    /** Set the Image. */
    fun image(image: Image?) = apply { this.image = image }

    override fun build(): Podcast.Googleplay? {
        val oCategories = if (categories.isEmpty()) null else Object()
        return if (anyNotNull(author, oCategories, description, explicit, image)) {
            Podcast.Googleplay(
                author      = author,
                categories  = immutableCopyOf(categories),
                description = description,
                explicit    = explicit,
                image       = image
            )
        } else {
            null
        }
    }

}
