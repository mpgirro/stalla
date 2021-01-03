package io.hemin.wien.model

/**
 * Model class for elements describing images.
 *
 * @property url The value of an RSS `<url>` element inside an `<image>`. Represents the image URL.
 * @property title The value of an RSS `<title>` element inside an `<image>`. Must match the containing podcast or episode title.
 * @property link The value of an RSS `<link>` element inside an `<image>`. Must match the containing podcast or episode link.
 * @property width The numeric value of an RSS `<width>` element inside an `<image>` element.
 * @property height The numeric value of an RSS `<height>` element inside an `<image>` element.
 * @property description The value of an RSS `<description>` element inside an `<image>`.
 */
data class Image(
    val url: String,
    val title: String? = null,
    val link: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val description: String? = null
)

// TODO turn into sealed class to make compile-safe the difference between RSS and non-RSS images (see appendImageElement)
