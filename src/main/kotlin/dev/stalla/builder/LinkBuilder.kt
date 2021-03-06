package dev.stalla.builder

import dev.stalla.model.MediaType
import dev.stalla.model.atom.Link
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [Link] instances.
 *
 * @since 1.0.0
 */
public interface LinkBuilder : Builder<Link> {

    /** Set the href value. */
    public fun href(href: String): LinkBuilder

    /** Set the hrefLang value. */
    public fun hrefLang(hrefLang: String?): LinkBuilder

    /** Set the hrefResolved value. */
    public fun hrefResolved(hrefResolved: String?): LinkBuilder

    /** Set the length value. */
    public fun length(length: String?): LinkBuilder

    /** Set the rel value. */
    public fun rel(rel: String?): LinkBuilder

    /** Set the title value. */
    public fun title(title: String?): LinkBuilder

    /** Set the type value. */
    public fun type(type: MediaType?): LinkBuilder

    override fun applyFrom(prototype: Link?): LinkBuilder =
        whenNotNull(prototype) { link ->
            href(link.href)
            hrefLang(link.hrefLang)
            hrefResolved(link.hrefResolved)
            length(link.length)
            rel(link.rel)
            title(link.title)
            type(link.type)
        }
}
