package dev.stalla.builder.fake

import dev.stalla.builder.RssImageBuilder
import dev.stalla.model.rss.RssImage

internal class FakeRssImageBuilder : FakeBuilder<RssImage>(), RssImageBuilder {

    var url: String? = null
    var title: String? = null
    var link: String? = null
    var width: Int? = null
    var height: Int? = null
    var description: String? = null

    override fun url(url: String): RssImageBuilder = apply { this.url = url }

    override fun title(title: String): RssImageBuilder = apply { this.title = title }

    override fun link(link: String): RssImageBuilder = apply { this.link = link }

    override fun width(width: Int?): RssImageBuilder = apply { this.width = width }

    override fun height(height: Int?): RssImageBuilder = apply { this.height = height }

    override fun description(description: String?): RssImageBuilder = apply { this.description = description }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeRssImageBuilder) return false

        if (url != other.url) return false
        if (title != other.title) return false
        if (link != other.link) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (width ?: 0)
        result = 31 * result + (height ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakeRssImageBuilder(url=$url, title=$title, link=$link, width=$width, height=$height, description=$description)"
}
