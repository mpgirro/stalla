package io.hemin.wien.model

/**
 * An <image> tag model, of which there are different variants, used in different
 * contexts in an RSS feed.
 */
sealed class Image {     // STOPSHIP no need for a sealed class, after all

    /**
     * An `<image href="...">` tag]. The `href` attribute is _mandatory_.
     *
     * @property href The value of the `href` attribute. Represents the image URL.
     */
    data class HrefOnlyImage(val href: String) : Image()

    /**
     * An [RSS `<image>` tag][https://www.w3schools.com/XML/rss_tag_image.asp]. An
     * RSS image tag looks something like this:
     *
     * ```xml
     * <image>
     *   <url>https://example.com/image.png</url>
     *   <title>An example image</title>
     *   <link>https://example.com</link>
     *   <width>123</width>
     *   <height>456</height>
     *   <description>The image description.</description>
     * </image>
     * ```
     *
     * The `url`, `title` and `link` tags are _mandatory_, the rest are optional.
     *
     * @property url The value of an RSS `<url>` element inside an `<image>`. Represents the image URL.
     * @property title The value of an RSS `<title>` element inside an `<image>`. **Must** match the containing podcast's or episode's `title`.
     * @property link The value of an RSS `<link>` element inside an `<image>`. **Must** match the containing podcast's or episode's `link`.
     * @property width The numeric value of an RSS `<width>` element inside an `<image>` element.
     * @property height The numeric value of an RSS `<height>` element inside an `<image>` element.
     * @property description The value of an RSS `<description>` element inside an `<image>`.
     */
    data class RssImage(
        val url: String,
        val title: String,
        val link: String,
        val width: Int? = null,
        val height: Int? = null,
        val description: String? = null
    ) : Image()
}
