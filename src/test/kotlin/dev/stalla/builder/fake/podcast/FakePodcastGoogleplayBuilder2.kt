package dev.stalla.builder.fake.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastGoogleplayBuilder2
import dev.stalla.model.googleplay.PodcastGoogleplay2

internal class FakePodcastGoogleplayBuilder2 : FakeBuilder<PodcastGoogleplay2>(), PodcastGoogleplayBuilder2 {

    var author: String? = null
    var owner: String? = null
    var categoryBuilders: MutableList<ItunesStyleCategoryBuilder2> = mutableListOf()
    var description: String? = null
    var explicit: Boolean? = null
    var block: Boolean? = null
    var imageBuilder: HrefOnlyImageBuilder? = null

    override fun author(author: String?): PodcastGoogleplayBuilder2 = apply { this.author = author }

    override fun owner(email: String?): PodcastGoogleplayBuilder2 = apply { this.owner = email }

    override fun addCategoryBuilder(categoryBuilder: ItunesStyleCategoryBuilder2): PodcastGoogleplayBuilder2 = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun description(description: String?): PodcastGoogleplayBuilder2 = apply { this.description = description }

    override fun explicit(explicit: Boolean?): PodcastGoogleplayBuilder2 = apply { this.explicit = explicit }

    override fun block(block: Boolean): PodcastGoogleplayBuilder2 = apply { this.block = block }

    override fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder2 = apply { this.imageBuilder = imageBuilder }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastGoogleplayBuilder2) return false

        if (author != other.author) return false
        if (owner != other.owner) return false
        if (categoryBuilders != other.categoryBuilders) return false
        if (description != other.description) return false
        if (explicit != other.explicit) return false
        if (block != other.block) return false
        if (imageBuilder != other.imageBuilder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = author?.hashCode() ?: 0
        result = 31 * result + (owner?.hashCode() ?: 0)
        result = 31 * result + categoryBuilders.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (explicit?.hashCode() ?: 0)
        result = 31 * result + (block?.hashCode() ?: 0)
        result = 31 * result + (imageBuilder?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakePodcastGooglePlayBuilder(author=$author, owner=$owner, categories=$categoryBuilders, description=$description, explicit=$explicit, " +
            "block=$block, imageBuilder=$imageBuilder)"
}
