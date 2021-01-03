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
}
