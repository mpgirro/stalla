package dev.stalla.builder.validating

import dev.stalla.builder.RssImageBuilder
import dev.stalla.model.rss.RssImage
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingRssImageBuilder : RssImageBuilder {

    private var url: String? = null
    private var title: String? = null
    private var link: String? = null

    private var width: Int? = null
    private var height: Int? = null
    private var description: String? = null

    override fun url(url: String): RssImageBuilder = apply { this.url = url }

    override fun title(title: String): RssImageBuilder = apply { this.title = title }

    override fun link(link: String): RssImageBuilder = apply { this.link = link }

    override fun width(width: Int?): RssImageBuilder = apply { this.width = width }

    override fun height(height: Int?): RssImageBuilder = apply { this.height = height }

    override fun description(description: String?): RssImageBuilder = apply { this.description = description }

    override val hasEnoughDataToBuild: Boolean
        get() = url != null && title != null && link != null

    override fun build(): RssImage? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return RssImage(
            url = checkRequiredProperty(::url),
            title = checkRequiredProperty(::title),
            link = checkRequiredProperty(::link),
            width = width,
            height = height,
            description = description
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingRssImageBuilder) return false

        if (url != other.url) return false
        if (title != other.title) return false
        if (link != other.link) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + (width ?: 0)
        result = 31 * result + (height ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String =
        "ValidatingRssImageBuilder(url='$url', title='$title', link='$link', width=$width, height=$height, description=$description)"
}
