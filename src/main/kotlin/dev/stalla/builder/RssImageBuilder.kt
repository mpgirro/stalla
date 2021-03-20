package dev.stalla.builder

import dev.stalla.model.rss.RssImage
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [RssImage] instances.
 *
 * @since 1.0.0
 */
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

    override fun applyFrom(prototype: RssImage?): RssImageBuilder =
        whenNotNull(prototype) { image ->
            url(image.url)
            title(image.title)
            link(image.link)
            width(image.width)
            height(image.height)
            description(image.description)
        }
}
