package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastGooglePlayBuilder : FakeBuilder<Podcast.GooglePlay>(), PodcastGooglePlayBuilder {

    var author: String? = null
    var owner: String? = null
    var categories: MutableList<String> = mutableListOf()
    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var image: Image? = null

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
}
