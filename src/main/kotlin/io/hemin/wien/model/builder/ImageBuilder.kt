package io.hemin.wien.model.builder

import io.hemin.wien.model.Image
import io.hemin.wien.model.builder.Builder.Companion.anyNotNull

/** Builder class for [Image] instances. */
class ImageBuilder : Builder<Image> {

    private var url: String?         = null
    private var title: String?       = null
    private var link: String?        = null
    private var width: Int?          = null
    private var height: Int?         = null
    private var description: String? = null

    /** Set the url. */
    fun url(url: String?) = apply { this.url = url }

    /** Set the title. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the link. */
    fun link(link: String?) = apply { this.link = link }

    /** Set the width. */
    fun width(width: Int?) = apply { this.width = width }

    /** Set the height. */
    fun height(height: Int?) = apply { this.height = height }

    /** Set the description. */
    fun description(description: String?) = apply { this.description = description }

    override fun build(): Image? {
        return if (anyNotNull(url, title, link, width, height, description))
            Image(
                url         = url,
                title       = title,
                link        = link,
                width       = width,
                height      = height,
                description = description
            )
        else
            null
    }

}
