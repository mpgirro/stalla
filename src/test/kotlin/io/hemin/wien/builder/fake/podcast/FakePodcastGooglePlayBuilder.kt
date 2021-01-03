package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastGooglePlayBuilder : FakeBuilder<Podcast.GooglePlay>(), PodcastGooglePlayBuilder {

    var author: String? = null
    var owner: String? = null
    var categories: MutableList<String> = mutableListOf()
    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var imageBuilder: ImageBuilder? = null

    override fun author(author: String?): PodcastGooglePlayBuilder = apply { this.author = author }

    override fun owner(email: String?): PodcastGooglePlayBuilder = apply { this.owner = email }

    override fun addCategory(category: String): PodcastGooglePlayBuilder = apply {
        categories.add(category)
    }

    override fun description(description: String?): PodcastGooglePlayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): PodcastGooglePlayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean?): PodcastGooglePlayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: ImageBuilder?): PodcastGooglePlayBuilder = apply { this.imageBuilder = imageBuilder }
}
