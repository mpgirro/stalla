package dev.stalla.builder.fake.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastGoogleplayBuilder
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.googleplay.PodcastGoogleplay

internal class FakePodcastGoogleplayBuilder : FakeBuilder<PodcastGoogleplay>(), PodcastGoogleplayBuilder {

    var author: String? = null
    var owner: String? = null
    var categories: MutableList<GoogleplayCategory> = mutableListOf()
    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var imageBuilder: HrefOnlyImageBuilder? = null

    override fun author(author: String?): PodcastGoogleplayBuilder = apply { this.author = author }

    override fun owner(email: String?): PodcastGoogleplayBuilder = apply { this.owner = email }

    override fun addCategory(category: GoogleplayCategory): PodcastGoogleplayBuilder = apply {
        categories.add(category)
    }

    override fun description(description: String?): PodcastGoogleplayBuilder = apply { this.description = description }

    override fun explicit(explicit: Boolean?): PodcastGoogleplayBuilder = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastGoogleplayBuilder = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder = apply { this.imageBuilder = imageBuilder }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastGoogleplayBuilder) return false

        if (author != other.author) return false
        if (owner != other.owner) return false
        if (categories != other.categories) return false
        if (description != other.description) return false
        if (explicit != other.explicit) return false
        if (block != other.block) return false
        if (imageBuilder != other.imageBuilder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = author?.hashCode() ?: 0
        result = 31 * result + (owner?.hashCode() ?: 0)
        result = 31 * result + categories.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (explicit?.hashCode() ?: 0)
        result = 31 * result + (block?.hashCode() ?: 0)
        result = 31 * result + (imageBuilder?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakePodcastGooglePlayBuilder(author=$author, owner=$owner, categories=$categories, description=$description, explicit=$explicit, " +
            "block=$block, imageBuilder=$imageBuilder)"
}
