package io.hemin.wien.builder.validating

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.model.Image

internal class ValidatingImageBuilder : ImageBuilder {

    private lateinit var urlValue: String

    private var title: String? = null
    private var link: String? = null
    private var width: Int? = null
    private var height: Int? = null
    private var description: String? = null

    override fun url(url: String): ImageBuilder = apply { this.urlValue = url }

    override fun title(title: String?): ImageBuilder = apply { this.title = title }

    override fun link(link: String?): ImageBuilder = apply { this.link = link }

    override fun width(width: Int?): ImageBuilder = apply { this.width = width }

    override fun height(height: Int?): ImageBuilder = apply { this.height = height }

    override fun description(description: String?): ImageBuilder = apply { this.description = description }

    override fun build(): Image? {
        if (!::urlValue.isInitialized) {
            return null
        }

        return Image(
            url = urlValue,
            title = title,
            link = link,
            width = width,
            height = height,
            description = description
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingImageBuilder) return false

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
