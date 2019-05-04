package io.hemin.wien.model

/**
 * Model class for elements describing images.
 *
 * @property url The value of an RSS `<url>` element inside an `<image>`.
 * @property title The value of an RSS `<title>` element inside an `<image>`.
 * @property link The value of an RSS `<link>` element inside an `<image>`.
 * @property width The numeric value of an RSS `<width>` element inside an `<image>` element.
 * @property height The numeric value of an RSS `<height>` element inside an `<image>` element.
 * @property description The value of an RSS `<description>` element inside an `<image>`.
 */
data class Image(
    val url: String?,
    val title: String?,
    val link: String?,
    val width: Int?,
    val height: Int?,
    val description: String?
)
