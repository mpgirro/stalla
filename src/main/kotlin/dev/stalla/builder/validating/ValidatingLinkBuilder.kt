package dev.stalla.builder.validating

import dev.stalla.builder.LinkBuilder
import dev.stalla.model.MediaType
import dev.stalla.model.atom.Link
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingLinkBuilder : LinkBuilder {

    private var href: String? = null

    private var hrefLang: String? = null
    private var hrefResolved: String? = null
    private var length: String? = null
    private var rel: String? = null
    private var title: String? = null
    private var type: MediaType? = null

    override fun href(href: String): LinkBuilder = apply { this.href = href }

    override fun hrefLang(hrefLang: String?): LinkBuilder = apply { this.hrefLang = hrefLang }

    override fun hrefResolved(hrefResolved: String?): LinkBuilder = apply { this.hrefResolved = hrefResolved }

    override fun length(length: String?): LinkBuilder = apply { this.length = length }

    override fun rel(rel: String?): LinkBuilder = apply { this.rel = rel }

    override fun title(title: String?): LinkBuilder = apply { this.title = title }

    override fun type(type: MediaType?): LinkBuilder = apply { this.type = type }

    override val hasEnoughDataToBuild: Boolean
        get() = href != null

    override fun build(): Link? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Link(
            href = checkRequiredProperty(::href),
            hrefLang = hrefLang,
            hrefResolved = hrefResolved,
            length = length,
            rel = rel,
            title = title,
            type = type
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingLinkBuilder) return false

        if (href != other.href) return false
        if (hrefLang != other.hrefLang) return false
        if (hrefResolved != other.hrefResolved) return false
        if (length != other.length) return false
        if (rel != other.rel) return false
        if (title != other.title) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = href.hashCode()
        result = 31 * result + (hrefLang?.hashCode() ?: 0)
        result = 31 * result + (hrefResolved?.hashCode() ?: 0)
        result = 31 * result + (length?.hashCode() ?: 0)
        result = 31 * result + (rel?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String =
        "ValidatingLinkBuilder(href='$href', hrefLang=$hrefLang, hrefResolved=$hrefResolved, length=$length, rel=$rel, title=$title, " +
            "type=$type)"
}
