package io.hemin.wien.builder

import io.hemin.wien.model.Link

/** Builder for constructing [Link] instances. */
interface LinkBuilder : Builder<Link> {

    /** Set the href value. */
    fun href(href: String): LinkBuilder

    /** Set the hrefLang value. */
    fun hrefLang(hrefLang: String?): LinkBuilder

    /** Set the hrefResolved value. */
    fun hrefResolved(hrefResolved: String?): LinkBuilder

    /** Set the length value. */
    fun length(length: String?): LinkBuilder

    /** Set the rel value. */
    fun rel(rel: String?): LinkBuilder

    /** Set the title value. */
    fun title(title: String?): LinkBuilder

    /** Set the type value. */
    fun type(type: String?): LinkBuilder

    override fun from(model: Link): LinkBuilder {
        return Link.builder()
            .href(model.href)
            .hrefLang(model.hrefLang)
            .hrefResolved(model.hrefResolved)
            .length(model.length)
            .rel(model.rel)
            .title(model.title)
            .type(model.type)
    }
}
