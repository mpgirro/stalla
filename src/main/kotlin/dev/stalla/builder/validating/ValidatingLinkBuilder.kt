package dev.stalla.builder.validating

import dev.stalla.builder.LinkBuilder
import dev.stalla.model.MediaType
import dev.stalla.model.atom.Link
import dev.stalla.util.InternalAPI2

@InternalAPI2
internal class ValidatingLinkBuilder : LinkBuilder {

    private lateinit var hrefValue: String

    private var hrefLang: String? = null
    private var hrefResolved: String? = null
    private var length: String? = null
    private var rel: String? = null
    private var title: String? = null
    private var type: MediaType? = null

    override fun href(href: String): LinkBuilder = apply { this.hrefValue = href }

    override fun hrefLang(hrefLang: String?): LinkBuilder = apply { this.hrefLang = hrefLang }

    override fun hrefResolved(hrefResolved: String?): LinkBuilder = apply { this.hrefResolved = hrefResolved }

    override fun length(length: String?): LinkBuilder = apply { this.length = length }

    override fun rel(rel: String?): LinkBuilder = apply { this.rel = rel }

    override fun title(title: String?): LinkBuilder = apply { this.title = title }

    override fun type(type: MediaType?): LinkBuilder = apply { this.type = type }

    override val hasEnoughDataToBuild: Boolean
        get() = ::hrefValue.isInitialized

    override fun build(): Link? {
        if (!hasEnoughDataToBuild) {
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
