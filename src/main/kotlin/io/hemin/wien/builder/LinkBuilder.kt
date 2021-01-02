package io.hemin.wien.builder

import io.hemin.wien.model.Link

/** Builder class for [Link] instances. */
class LinkBuilder : Builder<Link> {

    private lateinit var hrefValue: String
    private var hrefLang: String? = null
    private var hrefResolved: String? = null
    private var length: String? = null
    private var rel: String? = null
    private var title: String? = null
    private var type: String? = null

    /** Set the href value. */
    fun href(href: String) = apply { this.hrefValue = href }

    /** Set the hrefLang value. */
    fun hrefLang(hrefLang: String?) = apply { this.hrefLang = hrefLang }

    /** Set the hrefResolved value. */
    fun hrefResolved(hrefResolved: String?) = apply { this.hrefResolved = hrefResolved }

    /** Set the length value. */
    fun length(length: String?) = apply { this.length = length }

    /** Set the rel value. */
    fun rel(rel: String?) = apply { this.rel = rel }

    /** Set the title value. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the type value. */
    fun type(type: String?) = apply { this.type = type }

    override fun build(): Link {
        require(::hrefValue.isInitialized) { "The link href is mandatory" }

        return Link(
            href = hrefValue,
            hrefLang = hrefLang,
            hrefResolved = hrefResolved,
            length = length,
            rel = rel,
            title = title,
            type = type
        )
    }
}
