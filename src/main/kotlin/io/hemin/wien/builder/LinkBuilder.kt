package io.hemin.wien.builder

import io.hemin.wien.model.Link

class LinkBuilder : Builder<Link> {

    private var href: String?  = null
    private var hrefLang: String? = null
    private var hrefResolved: String?   = null
    private var length: String?   = null
    private var rel: String?   = null
    private var title: String?   = null
    private var type: String?   = null

    fun href(href: String?) = apply { this.href = href }

    fun hrefLang(hrefLang: String?) = apply { this.hrefLang = hrefLang }

    fun hrefResolved(hrefResolved: String?) = apply { this.hrefResolved = hrefResolved }

    fun length(length: String?) = apply { this.length = length }

    fun rel(rel: String?) = apply { this.rel = rel }

    fun title(title: String?) = apply { this.title = title }

    fun type(type: String?) = apply { this.type = type }

    override fun build(): Link? {
        return if (Builder.anyNotNull(href, hrefLang, hrefResolved, length, rel, title, type)) {
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
