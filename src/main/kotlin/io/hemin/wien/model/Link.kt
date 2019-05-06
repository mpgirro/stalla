package io.hemin.wien.model

/**
 * Model class for elements describing hyperlinks.
 *
 * @property href The href of the link.
 * @property hrefLang The hrefLang of the link.
 * @property hrefResolved The hrefResolved of the link.
 * @property length The length of the link.
 * @property rel The rel of the link.
 * @property title The title of the link.
 * @property type The type of the link.
 */
data class Link(
    val href: String?,
    val hrefLang: String?,
    val hrefResolved: String?,
    val length: String?,
    val rel: String?,
    val title: String?,
    val type: String?
)
