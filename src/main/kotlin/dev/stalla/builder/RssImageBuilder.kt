package dev.stalla.builder

import dev.stalla.model.RssImage
import dev.stalla.util.whenNotNull

/** Builder for constructing [RssImage] instances. */
public interface RssImageBuilder : Builder<RssImage> {

    /** Set the url value. */
    public fun url(url: String): RssImageBuilder

    /** Set the title value. */
    public fun title(title: String): RssImageBuilder

    /** Set the link value. */
    public fun link(link: String): RssImageBuilder

    /** Set the width value. */
    public fun width(width: Int?): RssImageBuilder

    /** Set the height value. */
    public fun height(height: Int?): RssImageBuilder

    /** Set the description value. */
    public fun description(description: String?): RssImageBuilder

    override fun from(model: RssImage?): RssImageBuilder = whenNotNull(model) { image ->
        url(image.url)
        title(image.title)
        link(image.link)
        width(image.width)
        height(image.height)
        description(image.description)
    }
}
