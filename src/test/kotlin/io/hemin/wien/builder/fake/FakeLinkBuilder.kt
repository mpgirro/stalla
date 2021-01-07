package io.hemin.wien.builder.fake

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.model.Link

internal class FakeLinkBuilder : FakeBuilder<Link>(), LinkBuilder {

    var hrefValue: String? = null
    var hrefLang: String? = null
    var hrefResolved: String? = null
    var length: String? = null
    var rel: String? = null
    var title: String? = null
    var type: String? = null

    override fun href(href: String): LinkBuilder = apply { this.hrefValue = href }

    override fun hrefLang(hrefLang: String?): LinkBuilder = apply { this.hrefLang = hrefLang }

    override fun hrefResolved(hrefResolved: String?): LinkBuilder = apply { this.hrefResolved = hrefResolved }

    override fun length(length: String?): LinkBuilder = apply { this.length = length }

    override fun rel(rel: String?): LinkBuilder = apply { this.rel = rel }

    override fun title(title: String?): LinkBuilder = apply { this.title = title }

    override fun type(type: String?): LinkBuilder = apply { this.type = type }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeLinkBuilder) return false

        if (hrefValue != other.hrefValue) return false
        if (hrefLang != other.hrefLang) return false
        if (hrefResolved != other.hrefResolved) return false
        if (length != other.length) return false
        if (rel != other.rel) return false
        if (title != other.title) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hrefValue.hashCode()
        result = 31 * result + (hrefLang?.hashCode() ?: 0)
        result = 31 * result + (hrefResolved?.hashCode() ?: 0)
        result = 31 * result + (length?.hashCode() ?: 0)
        result = 31 * result + (rel?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }

    override fun toString() =
        "FakeLinkBuilder(hrefValue='$hrefValue', hrefLang=$hrefLang, hrefResolved=$hrefResolved, length=$length, rel=$rel, title=$title, type=$type)"
}
