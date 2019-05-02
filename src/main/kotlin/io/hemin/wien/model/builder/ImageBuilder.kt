package io.hemin.wien.model.builder

import io.hemin.wien.model.Image

class ImageBuilder : Builder<Image>() {

    private var url: String?         = null
    private var title: String?       = null
    private var link: String?        = null
    private var width: Int?          = null
    private var height: Int?         = null
    private var description: String? = null

    fun url(url: String?) = apply { this.url = url }

    fun title(title: String?) = apply { this.title = title }

    fun link(link: String?) = apply { this.link = link }

    fun width(width: Int?) = apply { this.width = width }

    fun height(height: Int?) = apply { this.height = height }

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
