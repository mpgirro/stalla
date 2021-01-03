package io.hemin.wien.builder

import io.hemin.wien.model.Image

internal interface ImageBuilder : Builder<Image> {

    /** Set the url value. */
    fun url(url: String): ImageBuilder

    /** Set the title value. */
    fun title(title: String?): ImageBuilder

    /** Set the link value. */
    fun link(link: String?): ImageBuilder

    /** Set the width value. */
    fun width(width: Int?): ImageBuilder

    /** Set the height value. */
    fun height(height: Int?): ImageBuilder

    /** Set the description value. */
    fun description(description: String?): ImageBuilder
}
