package io.hemin.wien.model

/**
 * Model class for Image.
 *
 * @property url The url of an RSS `<image>`.
 * @property title The url of an RSS `<image>`.
 * @property link The url of an RSS `<image>`.
 * @property width The url of an RSS `<image>`.
 * @property height The url of an RSS `<image>`.
 * @property description The url of an RSS `<image>`.
 */
data class Image(
    val url: String?,
    val title: String?,
    val link: String?,
    val width: Int?,
    val height: Int?,
    val description: String?
)
