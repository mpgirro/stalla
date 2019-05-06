package io.hemin.wien.builder

import io.hemin.wien.model.Link

/** Builder class for [Link] instances. */
class LinkBuilder : Builder<Link> {

    private var href: String?  = null
    private var hrefLang: String? = null
    private var hrefResolved: String?   = null
    private var length: String?   = null
    private var rel: String?   = null
    private var title: String?   = null
    private var type: String?   = null

    /** Set the href. */
    fun href(href: String?) = apply { this.href = href }

    /** Set the hrefLang. */
    fun hrefLang(hrefLang: String?) = apply { this.hrefLang = hrefLang }

    /** Set the hrefResolved. */
    fun hrefResolved(hrefResolved: String?) = apply { this.hrefResolved = hrefResolved }

    /** Set the length. */
    fun length(length: String?) = apply { this.length = length }

    /** Set the rel. */
    fun rel(rel: String?) = apply { this.rel = rel }

    /** Set the title. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the type. */
    fun type(type: String?) = apply { this.type = type }

    override fun build(): Link? {
        return if (anyNotNull(href, hrefLang, hrefResolved, length, rel, title, type)) {
            Link(
                href         = href,
                hrefLang     = hrefLang,
                hrefResolved = hrefResolved,
                length       = length,
                rel          = rel,
                title        = title,
                type         = type
            )
        } else {
            null
        }
    }

}
