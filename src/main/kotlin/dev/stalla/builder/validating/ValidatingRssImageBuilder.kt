package dev.stalla.builder.validating

import dev.stalla.builder.RssImageBuilder
import dev.stalla.model.rss.RssImage
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingRssImageBuilder : RssImageBuilder {

    private lateinit var urlValue: String
    private lateinit var titleValue: String
    private lateinit var linkValue: String

    private var width: Int? = null
    private var height: Int? = null
    private var description: String? = null

    override fun url(url: String): RssImageBuilder = apply { this.urlValue = url }

    override fun title(title: String): RssImageBuilder = apply { this.titleValue = title }

    override fun link(link: String): RssImageBuilder = apply { this.linkValue = link }

    override fun width(width: Int?): RssImageBuilder = apply { this.width = width }

    override fun height(height: Int?): RssImageBuilder = apply { this.height = height }

    override fun description(description: String?): RssImageBuilder = apply { this.description = description }

    override val hasEnoughDataToBuild: Boolean
        get() = ::urlValue.isInitialized && ::titleValue.isInitialized && ::linkValue.isInitialized

    override fun build(): RssImage? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return RssImage(
            url = urlValue,
            title = titleValue,
            link = linkValue,
            width = width,
            height = height,
            description = description
        )
    }
}
