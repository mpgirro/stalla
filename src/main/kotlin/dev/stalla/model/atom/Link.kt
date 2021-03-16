package dev.stalla.model.atom

import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.validating.ValidatingLinkBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.MediaType
import dev.stalla.model.atom.Atom.Factory.builder
import dev.stalla.model.atom.AtomPerson.Factory.builder
import dev.stalla.model.atom.Link.Factory.builder

/**
 * Model class for elements describing hyperlinks.
 *
 * Direct instantiation from Java is discouraged. Use the [builder] method
 * to obtain a builder instance for expressive construction instead.
 *
 * @property href The `href` attribute textContent of the `<atom:link>` element.
 * @property hrefLang The `hrefLang` attribute textContent of the `<atom:link>` element.
 * @property hrefResolved The `hrefResolved` attribute textContent of the `<atom:link>` element.
 * @property length The `length` attribute textContent of the `<atom:link>` element.
 * @property rel The `rel` attribute textContent of the `<atom:link>` element.
 * @property title The `title` attribute textContent of the `<atom:link>` element.
 * @property type The `type` attribute textContent of the `<atom:link>` element as a [MediaType].
 *
 * @since 1.0.0
 */
public data class Link(
    val href: String,
    val hrefLang: String? = null,
    val hrefResolved: String? = null,
    val length: String? = null,
    val rel: String? = null,
    val title: String? = null,
    val type: MediaType? = null
) {

    /** Provides a builder for the [Link] class. */
    public companion object Factory : BuilderFactory<Link, LinkBuilder> {

        /** Returns a builder implementation for building [Link] model instances. */
        @JvmStatic
        override fun builder(): LinkBuilder = ValidatingLinkBuilder()
    }
}
