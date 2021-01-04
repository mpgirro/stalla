package io.hemin.wien.builder.validating

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.model.Link

internal class ValidatingLinkBuilder : LinkBuilder {

    private lateinit var hrefValue: String

    private var hrefLang: String? = null
    private var hrefResolved: String? = null
    private var length: String? = null
    private var rel: String? = null
    private var title: String? = null
    private var type: String? = null

    override fun href(href: String): LinkBuilder = apply { this.hrefValue = href }

    override fun hrefLang(hrefLang: String?): LinkBuilder = apply { this.hrefLang = hrefLang }

    override fun hrefResolved(hrefResolved: String?): LinkBuilder = apply { this.hrefResolved = hrefResolved }

    override fun length(length: String?): LinkBuilder = apply { this.length = length }

    override fun rel(rel: String?): LinkBuilder = apply { this.rel = rel }

    override fun title(title: String?): LinkBuilder = apply { this.title = title }

    override fun type(type: String?): LinkBuilder = apply { this.type = type }

    override fun build(): Link? {
        if (!::hrefValue.isInitialized) {
            return null
        }

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
