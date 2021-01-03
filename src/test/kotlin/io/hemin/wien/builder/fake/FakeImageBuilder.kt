package io.hemin.wien.builder.fake

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.model.Image

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeImageBuilder : FakeBuilder<Image>(), ImageBuilder {

    lateinit var urlValue: String

    var title: String? = null
    var link: String? = null
    var width: Int? = null
    var height: Int? = null
    var description: String? = null

    /** Set the url value. */
    override fun url(url: String): ImageBuilder = apply { this.urlValue = url }

    /** Set the title value. */
    override fun title(title: String?): ImageBuilder = apply { this.title = title }

    /** Set the link value. */
    override fun link(link: String?): ImageBuilder = apply { this.link = link }

    /** Set the width value. */
    override fun width(width: Int?): ImageBuilder = apply { this.width = width }

    /** Set the height value. */
    override fun height(height: Int?): ImageBuilder = apply { this.height = height }

    /** Set the description value. */
    override fun description(description: String?): ImageBuilder = apply { this.description = description }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeImageBuilder) return false

        if (urlValue != other.urlValue) return false
        if (title != other.title) return false
        if (link != other.link) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = urlValue.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (width ?: 0)
        result = 31 * result + (height ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }
}
