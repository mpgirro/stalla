package io.hemin.wien.builder

import com.google.common.collect.ImmutableList
import io.hemin.wien.model.Image
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.Podcast.Itunes.ShowType

/** Builder class for [Podcast.Itunes] instances. */
class PodcastItunesBuilder : Builder<Podcast.Itunes> {

    private var subtitle: String?               = null
    private var summary: String?                = null
    private var image: Image?                   = null
    private var keywords: String?               = null
    private var author: String?                 = null
    private var categories: MutableList<String> = mutableListOf()
    private var explicit: Boolean?              = null
    private var block: Boolean?                 = null
    private var complete: Boolean?              = null
    private var type: ShowType?                 = null
    private var owner: Person?                  = null

    /** Set the subtitle. */
    fun subtitle(subtitle: String?) = apply { this.subtitle = subtitle }

    /** Set the summary. */
    fun summary(summary: String?) = apply { this.summary = summary }

    /** Set the Image. */
    fun image(image: Image?) = apply { this.image = image }

    /** Set the keywords. */
    fun keywords(keywords: String?) = apply { this.keywords = keywords }

    /** Set the author. */
    fun author(author: String?) = apply { this.author = author }

    /**
     * Adds a category to the list of categories.
     *
     * @param category The category to add.
     */
    fun addCategory(category: String?) = apply {
        category?.let { this.categories.add(it) }
    }

    /** Set the explicit flag. */
    fun explicit(explicit: Boolean?) = apply { this.explicit = explicit }

    /** Set the block flag. */
    fun block(block: Boolean?) = apply { this.block = block }

    /** Set the complete flag. */
    fun complete(complete: Boolean?) = apply { this.complete = complete }

    /** Set the type. */
    fun type(type: String?) = apply { this.type = ShowType.of(type) }

    /** Set the Person representing the owner. */
    fun owner(owner: Person?) = apply { this.owner = owner }

    override fun build(): Podcast.Itunes? {
        val immutableCategories = immutableCopyOf(categories)
        val oCategories = if (immutableCategories.isEmpty()) null else Object()

        return if (anyNotNull(subtitle, summary, image, keywords, author, oCategories, explicit, block, complete, type, owner)) {
            Podcast.Itunes(
                subtitle   = subtitle,
                summary    = summary,
                image      = image,
                keywords   = keywords,
                author     = author,
                categories = immutableCopyOf(categories),
                explicit   = explicit,
                block      = block,
                complete   = complete,
                type       = type,
                owner      = owner
            )
        } else {
            null
        }
    }

}
