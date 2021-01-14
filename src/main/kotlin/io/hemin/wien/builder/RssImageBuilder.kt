package io.hemin.wien.builder

import io.hemin.wien.model.RssImage

interface RssImageBuilder : Builder<RssImage> {

    /** Set the url value. */
    fun url(url: String): RssImageBuilder

    /** Set the title value. */
    fun title(title: String): RssImageBuilder

    /** Set the link value. */
    fun link(link: String): RssImageBuilder

    /** Set the width value. */
    fun width(width: Int?): RssImageBuilder

    /** Set the height value. */
    fun height(height: Int?): RssImageBuilder

    /** Set the description value. */
    fun description(description: String?): RssImageBuilder
}
