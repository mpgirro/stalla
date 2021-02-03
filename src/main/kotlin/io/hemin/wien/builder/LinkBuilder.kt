package io.hemin.wien.builder

import io.hemin.wien.model.Link
import io.hemin.wien.util.whenNotNull

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

    override fun from(model: Link?): LinkBuilder = whenNotNull(model) { link ->
        href(link.href)
        hrefLang(link.hrefLang)
        hrefResolved(link.hrefResolved)
        length(link.length)
        rel(link.rel)
        title(link.title)
        type(link.type)
    }
}
