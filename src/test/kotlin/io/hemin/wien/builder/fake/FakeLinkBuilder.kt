package io.hemin.wien.builder.fake

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.model.Link

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeLinkBuilder : FakeBuilder<Link>(), LinkBuilder {

    lateinit var hrefValue: String
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
}
